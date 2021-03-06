package sktest.ling.rr;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.rr.Req;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.UUID0;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReqTest extends SKUnit {

  @Test
  void build() {
    String cul33 = UUID0.cUl33();
    assertAll(
      () -> assertEquals("Req(ctx=null, enc=null, pri=null, pub=null)", HelloReq.build().toString()),

      () -> assertEquals("{}", OM3.writeValueAsString(HelloReq.build())),

      () -> assertEquals("{\"pri\":{}}", OM3.writeValueAsString(HelloReq.build(PriTest.HelloPri.build()))),
      () -> assertEquals("{\"pri\":{\"rtn\":\"rtn\"}}", OM3.writeValueAsString(HelloReq.build(PriTest.HelloPri.build("rtn")))),
      () -> assertEquals("{\"pri\":{\"obj\":\"obj\",\"rtn\":\"rtn\"}}", OM3.writeValueAsString(HelloReq.build(PriTest.HelloPri.build("rtn", "obj")))),
      () -> assertEquals("{\"pri\":{\"ext\":{\"userNo\":\"userNo\"},\"obj\":\"obj\",\"rtn\":\"rtn\"}}", OM3.writeValueAsString(HelloReq.build(PriTest.HelloPri.build("rtn", "obj", new PriTest.HelloPriExt().setUserNo("userNo"))))),

      () -> assertEquals("{\"pub\":{\"channelName\":\"channelName\",\"encoded\":\"N\",\"tenantName\":\"tenantName\",\"tracingNo\":\"" + cul33 + "\"}}", OM3.writeValueAsString(HelloReq.build(new HelloReqPub().setChannelName("channelName").setEncoded(String0.N).setTenantName("tenantName").setTracingNo(cul33)))),

      () -> assertEquals("{\"pri\":{},\"pub\":{\"channelName\":\"channelName\",\"encoded\":\"N\",\"tenantName\":\"tenantName\",\"tracingNo\":\"" + cul33 + "\"}}", OM3.writeValueAsString(HelloReq.build(new HelloReqPub().setChannelName("channelName").setEncoded(String0.N).setTenantName("tenantName").setTracingNo(cul33), PriTest.HelloPri.build()))),
      () -> assertEquals("{\"pri\":{\"rtn\":\"rtn\"},\"pub\":{\"channelName\":\"channelName\",\"encoded\":\"N\",\"tenantName\":\"tenantName\",\"tracingNo\":\"" + cul33 + "\"}}", OM3.writeValueAsString(HelloReq.build(new HelloReqPub().setChannelName("channelName").setEncoded(String0.N).setTenantName("tenantName").setTracingNo(cul33), PriTest.HelloPri.build("rtn")))),
      () -> assertEquals("{\"pri\":{\"obj\":\"obj\",\"rtn\":\"rtn\"},\"pub\":{\"channelName\":\"channelName\",\"encoded\":\"N\",\"tenantName\":\"tenantName\",\"tracingNo\":\"" + cul33 + "\"}}", OM3.writeValueAsString(HelloReq.build(new HelloReqPub().setChannelName("channelName").setEncoded(String0.N).setTenantName("tenantName").setTracingNo(cul33), PriTest.HelloPri.build("rtn", "obj")))),
      () -> assertEquals("{\"pri\":{\"ext\":{\"userNo\":\"userNo\"},\"obj\":\"obj\",\"rtn\":\"rtn\"},\"pub\":{\"channelName\":\"channelName\",\"encoded\":\"N\",\"tenantName\":\"tenantName\",\"tracingNo\":\"" + cul33 + "\"}}", OM3.writeValueAsString(HelloReq.build(new HelloReqPub().setChannelName("channelName").setEncoded(String0.N).setTenantName("tenantName").setTracingNo(cul33), PriTest.HelloPri.build("rtn", "obj", new PriTest.HelloPriExt().setUserNo("userNo"))))),

      () -> assertEquals("{\"enc\":\"enc\",\"pub\":{\"channelName\":\"channelName\",\"encoded\":\"N\",\"tenantName\":\"tenantName\",\"tracingNo\":\"" + cul33 + "\"}}", OM3.writeValueAsString(HelloReq.build(new HelloReqPub().setChannelName("channelName").setEncoded(String0.N).setTenantName("tenantName").setTracingNo(cul33), "enc"))),

      () -> assertEquals("{\"ctx\":{\"clientIp\":\"clientIp\",\"language\":\"zh_CN\"}}", OM3.writeValueAsString(HelloReq.build().setCtx(new HelloReqCtx().setClientIp("clientIp").setLanguage("zh_CN"))))
    );
  }

  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class HelloReq<O, R> extends Req<HelloReqCtx, PriTest.HelloPriExt, O, R, HelloReqPub> {

  }

  @Accessors(chain = true)
  @ToString
  public static class HelloReqCtx {
    @Getter
    @Setter
    private String clientIp;
    @Getter
    @Setter
    private String language;//default zh-CN, ref: http://www.rfc-editor.org/rfc/bcp/bcp47.txt
    //maybe some UserEntity here
  }

  @Accessors(chain = true)
  @ToString
  public static class HelloReqPub {
    @Getter
    @Setter
    private String channelName;
    @Getter
    @Setter
    private String encoded;//Y|N(default)
    @Getter
    @Setter
    private String tenantName;//if null same as channelName
    @Getter
    @Setter
    private String tracingNo;
    //some other open properties
  }
}
