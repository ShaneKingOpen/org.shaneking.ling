package org.shaneking.ling.persistence.entity.sql;

import javax.persistence.Transient;

@Deprecated//no scenario
public interface TenantWritable {
  @Transient
  String ERR_CODE__PERMISSION_DENIED = "TENANT_WRITABLE__PERMISSION_DENIED";
}
