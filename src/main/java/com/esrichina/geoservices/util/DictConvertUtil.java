package com.esrichina.geoservices.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.esrichina.geoservices.annotation.DictAnnotation;
import com.esrichina.geoservices.constant.ApplicationConstant;
import com.esrichina.geoservices.entity.TSysDictionaryEntity;
import com.esrichina.geoservices.redis.Redis;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 字典翻译工具类
 *
 * @author: LOONGER CHEN
 * @version: 1.0.0
 */
@Component
public class DictConvertUtil {


    @Resource
    private Redis redis;

    public static DictConvertUtil dictConvertUtil;

    @PostConstruct
    public void init() {
        dictConvertUtil = this;
    }


    /**
     * 对象：编码值转编码
     *
     * @param data
     * @param <T>
     */
    public static <T> void convertToCode(T data) {
        convertToCode(data, null);
    }

    /**
     * 对象：编码值转编码，然后函数对象
     *
     * @param data
     * @param consumer
     */
    public static <T> void convertToCode(T data, Consumer<T> consumer) {
        if (Objects.isNull(data)) {
            return;
        }
        convert(data, true);
        if (consumer != null) {
            consumer.accept(data);
        }
    }

    /**
     * 对象集合：编码值转编码
     *
     * @param data
     * @param <T>
     */
    public static <T> void convertToCodeList(List<T> data) {
        convertToCodeList(data, null);
    }

    /**
     * 对象集合：编码值转编码，然后函数对象
     *
     * @param data
     * @param consumer
     */
    public static <T> void convertToCodeList(List<T> data, Consumer<T> consumer) {
        if (Objects.isNull(data) || CollUtil.isEmpty(data)) {
            return;
        }
        data.parallelStream().forEach(d -> {
            convert(d, true);
            if (consumer != null) {
                consumer.accept(d);
            }
        });
    }

    /**
     * 对象：编码转编码值
     *
     * @param data
     * @param <T>
     */
    public static <T> void convertToDictionary(T data) {
        convertToDictionary(data, null);
    }

    /**
     * 对象：编码转编码值，然后执行函数对象
     *
     * @param data
     * @param consumer
     */
    public static <T> void convertToDictionary(T data, Consumer<T> consumer) {
        if (Objects.isNull(data)) {
            return;
        }
        convert(data, false);
        if (consumer != null) {
            consumer.accept(data);
        }
    }

    /**
     * 对象集合：编码转编码值
     *
     * @param data
     * @param <T>
     */
    public static <T> void convertToDictionaryList(List<T> data) {
        convertToDictionaryList(data, null);
    }

    /**
     * 对象集合：编码转编码值，然后执行函数对象
     *
     * @param data
     * @param consumer
     */
    public static <T> void convertToDictionaryList(List<T> data, Consumer<T> consumer) {
        if (Objects.isNull(data) || CollUtil.isEmpty(data)) {
            return;
        }
        data.parallelStream().forEach(d -> {
            convert(d, false);
            if (consumer != null) {
                consumer.accept(d);
            }
        });
    }

    /**
     * 转换字典中的值
     *
     * @param data
     * @param isToCode true:  编码值转编码，false：编码转编码值
     */
    private static <T> void convert(T data, boolean isToCode) {

        // 获取当前类的所有字段
        Field[] fields = data.getClass().getDeclaredFields();

        // 过滤 static、 final、private static final字段
        final List<Field> filteredFields = Stream.of(fields).filter(f -> !(f.getModifiers() == Modifier.FINAL
                || f.getModifiers() == Modifier.STATIC
                || f.getModifiers() == (Modifier.PRIVATE | Modifier.STATIC | Modifier.FINAL)
                || f.getAnnotation(DictAnnotation.class) == null)).collect(Collectors.toList());

        // 处理
        for (Field f : filteredFields) {

            // 获取当前字段注解
            DictAnnotation annotation = f.getAnnotation(DictAnnotation.class);

            // 没有加注解的字段不处理
            if (annotation == null) {
                continue;
            }

            // 反射获取字段值
            Object value;

            // 字典类型
            String dictType = annotation.value();

            // 如果是引用其他字段则值从其他字段取
            if (StrUtil.isNotEmpty(annotation.refField())) {
                value = ReflectUtil.getFieldValue(data, annotation.refField());
            }

            // 否则获取当前字段值
            else {
                value = ReflectUtil.getFieldValue(data, f);
            }

            // 转换字典时字段值为空 不进行后续处理
            if (value == null) {
                continue;
            }

            // 类型
            final Class<?> classType = value.getClass();

            // 如果不是基本类型
            if (!ClassUtil.isBasicType(classType) && classType != String.class) {
                // 是List 循环则递归调用
                if (value instanceof List) {
                    for (Object o : (List) value) {
                        convert(o, isToCode);
                    }
                }
                // 不是 List 则视为对象反射调用
                else {
                    convert(value, isToCode);
                }
            }

            // 自定义的字典
            final String dicts = annotation.dicts();

            // 转换字典时字段字典类型未配置（字典key都不配置转个毛线）
            if (StrUtil.isEmpty(dictType) && StrUtil.isEmpty(dicts)) {
                continue;
            }

            // 获取字典的对应 映射关系 （建议此处做缓存提高转换速度）
            final List<Dict> currDictList;

            if (StrUtil.isNotBlank(dicts)) {
                final List<String> dictList = StrUtil.splitTrim(dicts, ",");
                currDictList = Optional.ofNullable(dictList)
                        .filter(CollUtil::isNotEmpty)
                        .map(s -> s.parallelStream().map(d -> {
                            final List<String> dTrim = StrUtil.splitTrim(d, ":");
                            return dTrim.size() == 2 ? Dict.builder().dictValue(dTrim.get(0)).dictLabel(dTrim.get(1)).build() : null;
                        }).filter(Objects::nonNull).collect(Collectors.toList())).orElse(new ArrayList<>(0));

            } else {
                currDictList = getDictByDictType(dictType);
            }

            // 是否匹配到了字典中的值
            boolean isMatchSuccess = false;

            // 获取当前字典值
            final String beanValue = Convert.toStr(value);

            // 支持类似 , 逗号隔开的字典转换, 如果需要支持其他 DictConvert#delimiter() 可在此设置
            // eg : 兴趣爱好 （足球,篮球,奥利给）
            // 转换后则为 （football,basketball,aoligei）
            final String delimiter = annotation.delimiter();

            final List<String> beanValues = StrUtil.splitTrim(beanValue, delimiter);

            // 1 to 男
            if (!isToCode) {
                // 逗号隔开字典转换支持
                if (CollUtil.isNotEmpty(beanValues) && beanValues.size() > 1) {
                    final Map<String, String> dictMap = currDictList.stream().collect(Collectors.toMap(Dict::getDictValue, Dict::getDictLabel));
                    final List<String> matchesDict = beanValues.stream()
                            .filter(dictMap::containsKey)
                            .map(dm -> Objects.nonNull(dictMap.get(dm)) ? dictMap.get(dm) : "")
                            .collect(Collectors.toList());
                    if (CollUtil.isNotEmpty(matchesDict)) {
                        isMatchSuccess = true;
                        ReflectUtil.setFieldValue(data, f, CollUtil.join(matchesDict, delimiter));
                    }
                } else {
                    for (Dict sysDictData : currDictList) {
                        if (Objects.equals(Convert.toStr(value), sysDictData.getDictValue())) {
                            ReflectUtil.setFieldValue(data, f, Objects.nonNull(sysDictData.getDictLabel()) ? sysDictData.getDictLabel() : value);
                            isMatchSuccess = true;
                            break;
                        }
                    }
                }
            } else {
                // 逗号隔开字典转换支持
                if (CollUtil.isNotEmpty(beanValues) && beanValues.size() > 1) {
                    final Map<String, String> dictMap = currDictList.stream().collect(Collectors.toMap(Dict::getDictLabel, Dict::getDictValue));
                    final List<String> matchesDict = beanValues.stream()
                            .filter(dictMap::containsKey)
                            .map(dm -> Objects.nonNull(dictMap.get(dm)) ? dictMap.get(dm) : "")
                            .collect(Collectors.toList());
                    if (CollUtil.isNotEmpty(matchesDict)) {
                        isMatchSuccess = true;
                        ReflectUtil.setFieldValue(data, f, CollUtil.join(matchesDict, delimiter));
                    }
                } else {
                    for (Dict sysDictData : currDictList) {
                        if (Objects.equals(Convert.toStr(value), sysDictData.getDictLabel())) {
                            ReflectUtil.setFieldValue(data, f, Objects.nonNull(sysDictData.getDictValue()) ? sysDictData.getDictValue() : value);
                            isMatchSuccess = true;
                            break;
                        }
                    }
                }
            }

            // 如果匹配不到字典中的值 且 字段中明确表示如果匹配不到就置为NULL
            if (!isMatchSuccess && annotation.ifNotMatchConvertToNull()) {
                ReflectUtil.setFieldValue(data, f, null);
            }

        }
    }

    /**
     * 根据字典类型查询所有该类型的字典信息
     * 可以在此处添加缓存（加快转换速度）
     * eg:使用 spring cache
     *
     * @param dictType 字典类型
     * @return 匹配到的字典集合
     * @Cacheable(value = "dictConvert",key = "#dictType")
     * public static List<Dict> getDictByDictType(String dictType) {
     * return dictData;
     * }
     */
    private static List<Dict> getDictByDictType(String dictType) {
        return getAllDict().parallelStream().filter(d -> dictType.equals(d.getDictType())).collect(Collectors.toList());
    }

    /**
     * 获取所有字典
     * <p>
     * 字典信息 可以从数据库 查询 或者 放入缓存都可以
     */
    public static List<Dict> getAllDict() {
        List<Dict> data = new ArrayList<>();
        List<TSysDictionaryEntity> list = JSONUtil.toList((JSONArray) dictConvertUtil.redis.get(ApplicationConstant.BASE_DICTIONARY.getName()), TSysDictionaryEntity.class);
        list.stream().forEach(item -> {
            data.add(DictConvertUtil.Dict.builder().dictType(item.getIdentify()).dictLabel(item.getName()).dictValue(item.getCode()).build());
        });
        return data;
    }

    @Data
    @Builder
    public static class Dict {
        /**
         * dict type
         * eg: sex
         */
        private String dictType;

        /**
         * dict label
         * eg: 男
         */
        private String dictLabel;

        /**
         * dict value
         * eg: 1
         */
        private String dictValue;
    }

}
