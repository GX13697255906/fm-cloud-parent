package com.gx.cloud.admin.server.utils.generator;

/**
 * @author ：yangqingbo
 * @date ：Created in 2019/10/10 11:43
 * @description：
 */

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：yangqingbo
 * @date ：Created in 2019/9/18 10:02
 * @description：代码生成器
 */
@Service
public class CodeGenerator {

    /**
     * 代码生成工具
     */
    public static void execute(GenerateConfig generateConfig) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(generateConfig.getOutputDir());
        gc.setFileOverride(true);
        //ActiveRecord特性
        gc.setActiveRecord(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        gc.setEnableCache(false);
        // 自动打开输出目录
        gc.setOpen(false);
        gc.setAuthor(generateConfig.getAuthor());
        gc.setSwagger2(true);
        //主键策略
        gc.setIdType(IdType.ID_WORKER);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(generateConfig.getDbType());
        dsc.setDriverName(generateConfig.getJdbcDriver());
        dsc.setUrl(generateConfig.getJdbcUrl());
        dsc.setUsername(generateConfig.getJdbcUserName());
        dsc.setPassword(generateConfig.getJdbcPassword());
        // dsc.setTypeConvert(new DmTypeConvert());
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                //将数据库中NUMBER转换成double
                if (fieldType.toLowerCase().contains("number")) {
                    return DbColumnType.DOUBLE;
                }
                if (fieldType.toLowerCase().contains("bigint")) {
                    return DbColumnType.LONG;
                }
                if (fieldType.toLowerCase().contains("date")) {
                    return DbColumnType.DATE;
                }
                return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
            }
        });
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(false);
        strategy.setRestControllerStyle(true);
        strategy.setEntityLombokModel(true);
        strategy.setEntityTableFieldAnnotationEnable(false);
        // 此处可以移除表前缀表前缀
        strategy.setTablePrefix(generateConfig.getTablePrefix());

        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 字段生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityColumns("create_time", "update_time");
//        // mapper 父类
//        strategy.setSuperMapperClass("com.pot.cloud.common.mybatis.base.mapper.SuperMapper");
//        // 实体父类
//        strategy.setSuperEntityClass("com.pot.cloud.common.mybatis.base.entity.AbstractEntity");
//        // 接口父类
//        strategy.setSuperServiceClass("com.pot.cloud.common.mybatis.base.service.IBaseService");
//        // 接口实现类父类
//        strategy.setSuperServiceImplClass("com.pot.cloud.common.mybatis.base.service.impl.BaseServiceImpl");
//        // 需要生成的表
//        strategy.setInclude(generateConfig.getIncludeTables());

        ConfigBuilder config = new ConfigBuilder(new PackageConfig(), dsc, strategy, new TemplateConfig(), gc);
        List<TableInfo> list = config.getTableInfoList();

        // 公共字段填充
        ArrayList<TableFill> tableFills = new ArrayList<>();
//        tableFills.add(new TableFill("createTime", FieldFill.INSERT));
//        tableFills.add(new TableFill("updateTime", FieldFill.UPDATE));
        strategy.setTableFillList(tableFills);
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = new PackageConfig();
        //父包名
        pc.setParent(generateConfig.getParentPackage());
        //父包模块名
        pc.setModuleName(generateConfig.getModuleName());
        //实体类父包
        pc.setEntity("entity");
        //controller父包
        pc.setController("controller");
        //mapper父包
        pc.setMapper("mapper");
        //xml父包
        pc.setXml("xml");
        pc.setServiceImpl("service.impl");
        pc.setService("service");
        mpg.setPackageInfo(pc);
        // 执行生成
        mpg.execute();
    }

    public static void main(String[] args) {
        String[] tables = {
                "file"
                // "TBL_CON_GAFIT_B", "TBL_CON_PARTITION_B_LF_96POINT", "TBL_CON_PARTITION_B_PRICE_ACM", "TBL_CON_PARTITION_B_PRICE_DAM", "TBL_CON_PLANT_B_PLAN_OUTPUT", "TBL_CON_PWRGRID_B_LF_96POINT",
                // "TBL_CON_SYSTEM_B_PRICE_ACM", "TBL_CON_SYSTEM_B_PRICE_DAM", "TBL_CON_TIELINE_H_15MIN", "TBL_MLT_DECOMPOSE_B_DAY", "TBL_PWDS_DECLARE_B_DAM", "TBL_PWDS_PRICE_B_ACM",
                // "TBL_PWDS_PRICE_B_DAM", "TBL_PWDS_RESULT_B_DAM", "TBL_PWGS_DECLARE_B_ACM", "TBL_PWGS_DECLARE_B_DAM", "TBL_PWGS_RESULT_B_DAM", "TBL_CON_PARTITION_B_EXCHANGE_DAM",
               // "TBL_CON_SECTION_H_DAY","TBL_DEV_PWRTRANSFM_H_DAY","TBl_MLT_DECOMPOSE_B_DAY"
        };//表名
        String url = "jdbc:mysql://139.199.84.234:3306/fm?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";   //数据库地址
        String userName = "root1";                //数据库用户名
        String password = "root1";       //数据库密码
        String driverName = "com.mysql.cj.jdbc.Driver";     //数据库驱动
        String type = "mysql";         //数据库类型
        String author = "xun.guo";       //作者
        String parentPackage = "com.gx.cloud.admin.server";     //父包名
        String moduleName = "";          //模块名
        String[] profix = {};      //去掉表前缀
        String outputDir = "E:\\outDir";      //生成文件输出地址
        GenerateConfig config = new GenerateConfig();
        config.setDbType(DbType.getDbType(type));
        config.setJdbcUrl(url);
        config.setJdbcUserName(userName);
        config.setJdbcPassword(password);
        config.setJdbcDriver(driverName);
        config.setAuthor(author);
        config.setParentPackage(parentPackage);
        config.setModuleName(moduleName);
        config.setIncludeTables(tables);
        config.setTablePrefix(profix);
        config.setOutputDir(outputDir);
        execute(config);
    }
}
