package com.etong.pms.domain;

import com.etong.pms.base.domain.BaseObj;
import java.util.List;
import lombok.Data;

/**
 * 部门详细类
 *
 * @author  王超华
 */
@Data
public class DeptDetail extends Dept {
  private List<Team> teams ;
}
