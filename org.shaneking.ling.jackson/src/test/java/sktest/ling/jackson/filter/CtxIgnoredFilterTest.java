package sktest.ling.jackson.filter;

import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.jackson.ctx.JacksonCtx;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.jackson.filter.CtxIgnoredFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CtxIgnoredFilterTest {

  @Test
  void include() {
    SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider();
    simpleFilterProvider.addFilter(CtxIgnoredFilter.FILTER_NAME, new CtxIgnoredFilter());
    OM3.om().setFilterProvider(simpleFilterProvider);
    Test4CtxIgnoredFilter test4CtxIgnoredFilter = new Test4CtxIgnoredFilter().setI1(1).setS1("s1").setS2("s2");
    assertEquals("{\"s1\":\"s1\",\"i1\":1,\"s2\":\"s2\"}", OM3.writeValueAsString(test4CtxIgnoredFilter));
    JacksonCtx.scenario.set("scenario2");
    assertEquals("{\"s1\":\"s1\",\"i1\":1}", OM3.writeValueAsString(test4CtxIgnoredFilter));
  }
}