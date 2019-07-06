package me.rainstorm.boot.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 * 默认的 Dao 类后缀是 Mapper，该插件修改自 RenameExampleClassPlugin，支持自定义 Dao 类名
 * {@code
 * Usage：
 * <plugin type="me.rainstorm.boot.mybatis.generator.plugin.RenameMapperClassPlugin">
 *      <property name="searchString" value="Mapper$"/>
 *      <property name="replaceString" value="Dao"/>
 * </plugin>
 * }
 * <p>
 * 上面实例将默认的 Mapper 后缀修改为 Dao
 *
 * @author baochen1.zhang
 * @date 2019.07.06
 * @see org.mybatis.generator.plugins.RenameExampleClassPlugin
 */
public class RenameMapperClassPlugin extends PluginAdapter {

    private String searchString;
    private String replaceString;
    private Pattern pattern;

    public RenameMapperClassPlugin() {
    }

    @Override
    public boolean validate(List<String> warnings) {

        searchString = properties.getProperty("searchString"); //$NON-NLS-1$
        replaceString = properties.getProperty("replaceString"); //$NON-NLS-1$

        boolean valid = stringHasValue(searchString)
                && stringHasValue(replaceString);

        if (valid) {
            pattern = Pattern.compile(searchString);
        } else {
            if (!stringHasValue(searchString)) {
                warnings.add(getString("ValidationError.18", //$NON-NLS-1$
                        "RenameMapperClassPlugin", //$NON-NLS-1$
                        "searchString")); //$NON-NLS-1$
            }
            if (!stringHasValue(replaceString)) {
                warnings.add(getString("ValidationError.18", //$NON-NLS-1$
                        "RenameMapperClassPlugin", //$NON-NLS-1$
                        "replaceString")); //$NON-NLS-1$
            }
        }

        return valid;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        String oldType = introspectedTable.getMyBatis3JavaMapperType();
        Matcher matcher = pattern.matcher(oldType);
        String newType = matcher.replaceAll(replaceString);

        introspectedTable.setMyBatis3JavaMapperType(newType);
    }
}
