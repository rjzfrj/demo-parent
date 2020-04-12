Proto 简单的对象示例
第一步定义proto文件
第二步引入依赖包这个包是在proto文件被编译后生成的具体的java对象文件才会使用
  <dependency>
            <groupId>com.google.protobuf</groupId>
            <artifactId>protobuf-java</artifactId>
            <version>3.11.4</version>
        </dependency>
        需要注意依赖包和编译工具的版本对应问题，不对应可能出现问题