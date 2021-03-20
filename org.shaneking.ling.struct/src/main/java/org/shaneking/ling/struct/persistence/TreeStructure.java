package org.shaneking.ling.struct.persistence;

/**
 * Most hierarchy has right of inheritance
 */
public interface TreeStructure {
  String NODE_TYPE__ROOT = "R";
  String NODE_TYPE__BRANCH = "B";
  String NODE_TYPE__LEAF = "L";

//  String getNodeName();

//  String getNodeDesc();

  String getNodeType();// like `R(Root)|B(Branch)|L(Leaf)`

  String getNodePath();// like `/root/xxx/yyy/zzz/pId/id/`

  String getNodePid();
}
