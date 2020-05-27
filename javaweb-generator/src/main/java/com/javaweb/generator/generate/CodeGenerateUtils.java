package com.javaweb.generator.generate;

import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.StringUtils;
import freemarker.template.Template;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Data
public class CodeGenerateUtils {

    /**
     * 作者
     */
    @Value("${generate.author}")
    private String author = "";
    /**
     * 创建时间
     */
    private String createTime = DateUtils.getDate();
    /**
     * 数据表名
     */
    private String tableName = "";
    /**
     * 数据表前缀
     */
    @Value("${generate.tablePrefix}")
    private String tablePredix = "";
    /**
     * 表描述
     */
    private String tableAnnotation = "";
    /**
     * 包名
     */
    @Value("${generate.packageName}")
    private String packageName = "";
    /**
     * 模块名
     */
    @Value("${generate.moduleName}")
    private String moduleName = "";
    /**
     * 自动去除表前缀
     */
    @Value("${generate.autoRemovePre}")
    private boolean autoRemovePre = false;
    /**
     * 数据库连接池
     */
    @Value("${spring.datasource.url}")
    private String url = "";
    /**
     * 数据库用户名
     */
    @Value("${spring.datasource.username}")
    private String username = "";
    /**
     * 数据库密码
     */
    @Value("${spring.datasource.password}")
    private String password = "";
    /**
     * 数据库驱动
     */
    private String driver = "com.mysql.cj.jdbc.Driver";
    /**
     * 项目根目录
     */
    String projectPath = System.getProperty("user.dir");
    private String targetPath = "";
    /**
     * 实体对象名
     */
    private String entityName = "";

    /**
     * 连接数据库
     *
     * @return
     * @throws Exception
     */
    public Connection getConnection() throws Exception {
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    /**
     * 程序主入口
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
        codeGenerateUtils.generateFile("sys_demo", "演示");
    }

    /**
     * 根据模板创建文件
     *
     * @throws Exception
     */
    public void generateFile(String tableName, String tableAnnotation) throws Exception {
        try {
            // 数据表名
            this.tableName = tableName;
            // 数据表描述
            this.tableAnnotation = tableAnnotation;
            // 实体对象名
            if (this.autoRemovePre) {
                this.entityName = replaceUnderLineAndUpperCase(tableName.replace(this.tablePredix, ""));
            } else {
                this.entityName = replaceUnderLineAndUpperCase(tableName);
            }
            // 目标文件路径
            String[] packageArr = packageName.split("\\.");
            targetPath = projectPath + "/" + moduleName + "/src/main/java/" + StringUtils.join(packageArr, "/");

            // 获取数据表信息
            Connection connection = getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");

            // 获取数据表列信息
            Map<String, Object> dataMap = getColumnsList(resultSet);

            /**
             * 生成实体Entity文件
             */
            generateEntityFile(dataMap);
            /**
             * 生成Mapper文件
             */
            generateMapperFile();
            /**
             * 生成Dao文件
             */
            generateDaoFile();
            /**
             * 生成查询条件文件
             */
            generateQueryFile(dataMap);
            /**
             * 生成服务类接口文件
             */
            generateIServiceFile();
            /**
             * 生成服务类接口实现文件
             */
            generateServiceImplFile(dataMap);
            /**
             * 生成实体列表Vo
             */
            generateEntityListVoFile(dataMap);
            /**
             * 生成实体表单Vo
             */
            generateEntityInfoVoFile(dataMap);
            /**
             * 生成控制器
             */
            generateControllerFile();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

        }
    }

    /**
     * 生成实体对象Entity.java文件
     *
     * @param dataMap 参数
     * @throws Exception
     */
    private void generateEntityFile(Map<String, Object> dataMap) throws Exception {
        // 文件路径
        String path = targetPath + "/entity/";
        // 初始化文件路径
        initFileDir(path);
        // 文件后缀
        String suffix = ".java";
        // 完整的文件路径
        String filePath = path + entityName + suffix;
        // 模板文件
        String templateName = "Entity.ftl";
        File entityFile = new File(filePath);
        generateFileByTemplate(templateName, entityFile, dataMap);
    }

    /**
     * 生成Mapper.xml文件
     *
     * @throws Exception
     */
    private void generateMapperFile() throws Exception {
        // 文件路径
        String path = projectPath + "/javaweb-admin/src/main/resources/mapper/";
        // 初始化文件路径
        initFileDir(path);
        // 文件后缀
        String suffix = "Mapper.xml";
        // 完整的文件路径
        String filePath = path + entityName + suffix;
        // 模板文件
        String templateName = "Mapper.ftl";
        File mapperFile = new File(filePath);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    /**
     * 生成Dao.java文件
     *
     * @throws Exception
     */
    private void generateDaoFile() throws Exception {
        // 文件路径
        String path = targetPath + "/mapper/";
        // 初始化文件路径
        initFileDir(path);
        // 文件后缀
        String suffix = "Mapper.java";
        // 完整的文件路径
        String filePath = path + entityName + suffix;
        // 模板文件
        String templateName = "Dao.ftl";
        File daoFile = new File(filePath);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, daoFile, dataMap);
    }

    /**
     * 生成Query.java查询文件
     *
     * @param dataMap 参数
     * @throws Exception
     */
    private void generateQueryFile(Map<String, Object> dataMap) throws Exception {
        // 文件路径
        String path = targetPath + "/query/";
        // 初始化文件路径
        initFileDir(path);
        // 文件后缀
        String suffix = "Query.java";
        // 完整的文件路径
        String filePath = path + entityName + suffix;
        // 模板文件
        String templateName = "Query.ftl";
        File queryFile = new File(filePath);
        generateFileByTemplate(templateName, queryFile, dataMap);
    }

    /**
     * 生成服务接口文件
     *
     * @throws Exception
     */
    private void generateIServiceFile() throws Exception {
        // 文件路径
        String path = targetPath + "/service/";
        // 初始化文件路径
        initFileDir(path);
        // 文件前缀
        String prefix = "I";
        // 文件后缀
        String suffix = "Service.java";
        // 完整的文件路径
        String filePath = path + prefix + entityName + suffix;
        // 模板文件
        String templateName = "IService.ftl";
        File serviceFile = new File(filePath);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, serviceFile, dataMap);
    }

    /**
     * 生成服务类实现文件
     *
     * @throws Exception
     */
    private void generateServiceImplFile(Map<String, Object> dataMap) throws Exception {
        // 文件路径
        String path = targetPath + "/service/impl/";
        // 初始化文件路径
        initFileDir(path);
        // 文件后缀
        String suffix = "ServiceImpl.java";
        // 完整的文件路径
        String filePath = path + entityName + suffix;
        // 模板文件
        String templateName = "ServiceImpl.ftl";
        File serviceImplFile = new File(filePath);
        generateFileByTemplate(templateName, serviceImplFile, dataMap);
    }

    /**
     * 生成列表ListVo文件
     *
     * @param dataMap 参数
     * @throws Exception
     */
    private void generateEntityListVoFile(Map<String, Object> dataMap) throws Exception {
        // 文件路径
        String path = targetPath + "/vo/" + entityName.toLowerCase() + "/";
        // 初始化文件路径
        initFileDir(path);
        // 文件后缀
        String suffix = ".java";
        // 完整的文件路径
        String filePath = path + entityName + "ListVo" + suffix;
        // 模板文件
        String templateName = "EntityListVo.ftl";
        File listVoFile = new File(filePath);
        generateFileByTemplate(templateName, listVoFile, dataMap);
    }

    /**
     * 生成表单InfoVo文件
     *
     * @param dataMap 参数
     * @throws Exception
     */
    private void generateEntityInfoVoFile(Map<String, Object> dataMap) throws Exception {
        // 文件路径
        String path = targetPath + "/vo/" + entityName.toLowerCase() + "/";
        // 初始化文件路径
        initFileDir(path);
        // 文件后缀
        String suffix = ".java";
        // 完整的文件路径
        String filePath = path + entityName + "InfoVo" + suffix;
        // 模板文件
        String templateName = "EntityInfoVo.ftl";
        File infoVoFile = new File(filePath);
        generateFileByTemplate(templateName, infoVoFile, dataMap);
    }

    /**
     * 生成控制器文件
     *
     * @throws Exception
     */
    private void generateControllerFile() throws Exception {
        // 文件路径
        String path = targetPath + "/controller/";
        // 初始化文件路径
        initFileDir(path);
        // 文件后缀
        String suffix = "Controller.java";
        // 完整的文件路径
        String filePath = path + entityName + suffix;
        // 模板文件
        String templateName = "Controller.ftl";
        File controllerFile = new File(filePath);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, controllerFile, dataMap);
    }

    /**
     * 生成模板文件
     *
     * @param templateName 模板名称
     * @param file         生成文件
     * @param dataMap      生成参数
     * @throws Exception
     */
    private void generateFileByTemplate(String templateName, File file, Map<String, Object> dataMap) throws Exception {
        Template template = FreeMarkerUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        dataMap.put("tableName", tableName);
        dataMap.put("entityName", entityName);
        dataMap.put("author", author);
        dataMap.put("date", createTime);
        dataMap.put("packageName", packageName);
        dataMap.put("tableAnnotation", tableAnnotation);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);
    }

    /**
     * 获取数据表列信息
     *
     * @param resultSet
     * @return
     * @throws IOException
     */
    private Map<String, Object> getColumnsList(ResultSet resultSet) throws IOException {
        // 初始化Map对象
        Map<String, Object> dataMap = new HashMap<>();
        try {
            // 获取列信息
            List<ColumnClass> columnClassList = new ArrayList<>();
            ColumnClass columnClass = null;
            while (resultSet.next()) {
                //id字段略过
                if (resultSet.getString("COLUMN_NAME").equals("id")) continue;
                columnClass = new ColumnClass();
                //获取字段名称
                columnClass.setColumnName(resultSet.getString("COLUMN_NAME"));
                //获取字段类型
                columnClass.setColumnType(resultSet.getString("TYPE_NAME"));
                //转换字段名称，如 sys_name 变成 SysName
                columnClass.setChangeColumnName(replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME")));
                //字段在数据库的注释
                columnClass.setColumnComment(resultSet.getString("REMARKS"));
                columnClassList.add(columnClass);
            }
            dataMap.put("model_column", columnClassList);
        } catch (Exception e) {

        }
        return dataMap;
    }

    /**
     * 根据路径创建文件夹
     *
     * @param path 路径
     */
    private void initFileDir(String path) {
        // 文件路径
        File file = new File(path);
        // 判断文件路径是否存在
        if (!file.exists()) {
            // 创建文件路径
            file.mkdirs();
        }
    }

    /**
     * 字符串转换函数
     * 如：sys_name 变成 SysName
     *
     * @param str 字符串
     * @return
     */
    public String replaceUnderLineAndUpperCase(String str) {
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        int count = sb.indexOf("_");
        while (count != 0) {
            int num = sb.indexOf("_", count);
            count = num + 1;
            if (num != -1) {
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count, count + 1, ia + "");
            }
        }
        String result = sb.toString().replaceAll("_", "");
        return StringUtils.capitalize(result);
    }

}
