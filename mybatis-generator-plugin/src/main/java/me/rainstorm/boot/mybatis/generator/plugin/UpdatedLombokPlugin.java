package me.rainstorm.boot.mybatis.generator.plugin;

import com.softwareloop.mybatis.generator.plugins.LombokPlugin;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * 取消 Lombok Plugin 给 Mapper 加的 @Mapper 注解
 *
 * @author baochen1.zhang
 * @date 2019.07.06
 * @see LombokPlugin#clientGenerated(Interface, TopLevelClass, IntrospectedTable)
 */
public class UpdatedLombokPlugin extends LombokPlugin {
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return true;
    }
}
