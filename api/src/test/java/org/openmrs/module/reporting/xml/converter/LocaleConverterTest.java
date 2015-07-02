package org.openmrs.module.reporting.xml.converter;

import org.junit.Assert;
import org.junit.Test;
import org.openmrs.module.reporting.report.util.ReportUtil;
import org.openmrs.module.reporting.xml.XmlReportSerializer;

import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;

public class LocaleConverterTest extends BaseConverterTest {

    protected String getXml() {
        return ReportUtil.readStringFromResource("org/openmrs/module/reporting/xml/converter/locale.xml");
    }

    @Test
    public void testMarshall() throws Exception {
        XmlReportSerializer serializer = getSerializer();
        serializer.alias("sample", Sample.class);
        Sample sample = serializer.fromXml(Sample.class, getXml());

        Assert.assertThat(sample.localeAttribute, is(Locale.UK));
        Assert.assertThat(sample.localeElement, is(Locale.FRENCH));
    }

    class Sample {
        public Locale localeElement;
        public Locale localeAttribute;
    }
}