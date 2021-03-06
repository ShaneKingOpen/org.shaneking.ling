package sktest.ling.jackson.filter;

import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.jackson.ctx.JacksonCtx;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.jackson.filter.CtxIgnoredFilter;
import org.shaneking.ling.test.SKUnit;

import static org.junit.jupiter.api.Assertions.*;

class CtxIgnoredFilterTest extends SKUnit {

  @Test
  void aaa() {
    assertAll(
      () -> assertNotNull(new CtxIgnoredFilterPrepare().include(new BeanPropertyWriterPrepare()))
    );
  }

  @Test
  void include() {
    SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider();
    simpleFilterProvider.addFilter(CtxIgnoredFilter.FILTER_NAME, new CtxIgnoredFilter());
    OM3.om().setFilterProvider(simpleFilterProvider);
    assertAll(
      () -> {
        CtxIgnoredFilterPrepare ctxIgnoredFilterPrepare = new CtxIgnoredFilterPrepare().setI1(1).setS1("s11").setS2("s12");
        JacksonCtx.scenario.set(null);
        assertEquals("{\"s1\":\"s11\",\"i1\":1,\"s2\":\"s12\",\"o1\":null}", OM3.writeValueAsString(ctxIgnoredFilterPrepare));
        JacksonCtx.scenario.set("scenario2");
        assertEquals("{\"s1\":\"s11\",\"i1\":1,\"o1\":null}", OM3.writeValueAsString(ctxIgnoredFilterPrepare));
      },
      () -> {
        CtxIgnoredFilterPrepare ctxIgnoredFilterPrepare = new CtxIgnoredFilterPrepare().setI1(1).setS1("s11").setS2("s12").setO1(new CtxIgnoredFilterPrepare.HelloCtxIgnoredFilter2().setI1(2).setS1("s21").setS2("s22"));
        JacksonCtx.scenario.set(null);
        assertEquals("{\"s1\":\"s11\",\"i1\":1,\"s2\":\"s12\",\"o1\":{\"s1\":\"s21\",\"i1\":2,\"s2\":\"s22\"}}", OM3.writeValueAsString(ctxIgnoredFilterPrepare));
        JacksonCtx.scenario.set("scenario2");
        assertEquals("{\"s1\":\"s11\",\"i1\":1,\"o1\":{\"s1\":\"s21\",\"i1\":2}}", OM3.writeValueAsString(ctxIgnoredFilterPrepare));
      }
    );

  }
}
