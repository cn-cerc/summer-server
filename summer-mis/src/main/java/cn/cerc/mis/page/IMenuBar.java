package cn.cerc.mis.page;

import cn.cerc.mis.core.IForm;
import cn.cerc.ui.core.UrlRecord;

import java.util.List;

public interface IMenuBar {
    // 登记菜单栏菜单项
    public int enrollMenu(IForm form, List<UrlRecord> menus);
}
