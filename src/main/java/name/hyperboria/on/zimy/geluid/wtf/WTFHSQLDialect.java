package name.hyperboria.on.zimy.geluid.wtf;

import org.hibernate.dialect.HSQLDialect;

/**
 * TODO template javadoc
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 31.07.14
 */
public class WTFHSQLDialect extends HSQLDialect {
    @Override
    protected String getDropSequenceString(String sequenceName) {
        return "drop sequence if exists " + sequenceName;
    }

    @Override
    public boolean dropConstraints() {
        return false;
    }
}
