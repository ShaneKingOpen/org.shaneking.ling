package sktest.ling.zero.text;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.text.MF0;

import static org.junit.jupiter.api.Assertions.*;

class MF0Test {

  @Test
  void aaa() {
    assertAll(
      () -> assertNotNull(new MF0()),
      () -> assertThrows(NullPointerException.class, () -> MF0.fmt(null, null))
    );
  }

  @Test
  void fmt() {
    assertAll(
      () -> assertEquals(" '/a/b.c' t ',' '\"' '\\n' (`a`,b,'c')", MF0.fmt(" '{0}' {1} ',' '\"' '\\n' ({2})", "/a/b.c", "t", "`a`,b,'c'")),
      () -> assertEquals(" '{0}' {0} '`a`,b,'c'' `a`,b,'c'", MF0.fmt(" '{0}' {0} '{1}' {1}", "{0}", "`a`,b,'c'"))
    );
  }
}
