package com.kk.apollo.utils;

import com.kk.apollo.biz.model.common.CommonVo;
import org.apache.commons.beanutils.BeanUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 用于转换VO的数据类型
 */
public class VoDataTypeParser<T> {
    Properties prop = new Properties();

    /**
     * 添加lable所必须用的构造器
     *
     * @param uri
     */
    public VoDataTypeParser(String uri) {
        String baseUrl = this.getClass().getResource("/").getPath() + "properties" + "/" + "lableProperties" + "/";
        String url = baseUrl + "/" + uri;
        System.out.println(url);
        try {
            FileInputStream in = new FileInputStream(url);
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public VoDataTypeParser() {

    }

    /**
     * 用于插入前数据格式转换
     * commonvo => Obj
     * @param t
     * @param commonVoList
     * @return
     */
    public Object vo2Obj(T t, List<CommonVo> commonVoList) {
        try {
            Object o = t.getClass().newInstance();
            String[] filedName = this.getFiledName(t);
            for (CommonVo c : commonVoList) {
                String dataKey = c.getDataKey();
                String dataValue = c.getDataValue();
                for (String name : filedName) {
                    if (name.equals(dataKey)) {
                        BeanUtils.setProperty(o, dataKey, dataValue);
                        break;
                    }
                }
            }
            return o;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将对象转换为CommonVo类型
     *
     * @param t
     * @return
     */
    public List<CommonVo> toType(T t) {
        List<Map<String, String>> filedsInfo = this.getFiledsInfo(t);
        List list = new ArrayList<>(filedsInfo.size());
        for (int i = 0; i < filedsInfo.size(); i++) {
            CommonVo commonVo = new CommonVo();
            Map<String, String> map = filedsInfo.get(i);
            String name = map.get("name"); //属性名字
            String value = map.get("value"); //值 value
            String lable = prop.getProperty(name);//lable
            commonVo.setDataKey(name);
            commonVo.setLable(lable);
            commonVo.setDataValue(value);
            commonVo.setId(i + 1);
            switch (name) {
                case "userAvatar":
                    list.add(0, commonVo);
                    break;
                case "userBackimg":
                    list.add(1, commonVo);
                    break;
                case "userName":
//                    list.add(2,commonVo);
//                    break;
                default:
                    list.add(commonVo);
                    break;
            }
        }
        return list;
    }


    /**
     * if(type.equals("class java.lang.Boolean")){
     * ...
     * }
     * 自定义  获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     */
    private List<Map<String, String>> getFiledsInfo(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            Map<String, String> infoMap = new HashMap<>();
            infoMap.put("type", fields[i].getType().toString());
            infoMap.put("name", fields[i].getName());
            Object value = getFieldValueByName(fields[i].getName(), o);
            if (value == null) {
                infoMap.put("value", "");
            } else {
                //TODO 判断类型 如LIST等
                infoMap.put("value", value.toString());
            }
            list.add(infoMap);
        }
        return list;
    }


    /**
     * 根据属性名获取属性值
     */
    private Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取对象的所有属性值，返回一个对象数组
     */
    public Object[] getFiledValues(Object o) {
        String[] fieldNames = this.getFiledName(o);
        Object[] value = new Object[fieldNames.length];
        for (int i = 0; i < fieldNames.length; i++) {
            value[i] = this.getFieldValueByName(fieldNames[i], o);
        }
        return value;
    }

    /**
     * 获取属性名数组
     */
    private String[] getFiledName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getType());
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

//    /**
//     * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
//     */
//    private List<Map<String, Object>> getFiledsInfo(Object o) {
//        Field[] fields = o.getClass().getDeclaredFields();
//        String[] fieldNames = new String[fields.length];
//        List list = new ArrayList();
//        Map infoMap = null;
//        for (int i = 0; i < fields.length; i++) {
//            infoMap = new HashMap();
//            infoMap.put("type", fields[i].getType().toString());
//            infoMap.put("name", fields[i].getName());
//            infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
//            list.add(infoMap);
//        }
//        return list;
//    }

}
