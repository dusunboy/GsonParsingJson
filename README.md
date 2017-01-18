# GsonParsingJson

Gson泛型封装解析约定格式JSON串

## 使用 ##
```java
Gson gson = new GsonBuilder().registerTypeAdapter(BaseJsonBean.class,
                new BaseJsonBeanDeserializer<>(DemoBean.class)).create();
BaseJsonBean<DemoBean> baseJsonBean = gson.fromJson(json,
                new TypeToken<BaseJsonBean<DemoBean>>() {}.getType());
//获取对象
baseJsonBean.getData();
//获取数组
baseJsonBean.getDataList();
```
## 项目运行 ##
使用Android Studio运行项目,在MyClass类中使用右键找到MyClass.main()运行<br>

## 感谢 ##
项目中用到的泛型生成库 **[TypeBuilder](https://github.com/ikidou/TypeBuilder)**

## Licensed ##
许可证 [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
