package cn.cerc.ui.fields;

import cn.cerc.core.ClassConfig;
import cn.cerc.core.Record;
import cn.cerc.ui.SummerUI;
import cn.cerc.ui.core.HtmlWriter;
import cn.cerc.ui.core.UIOriginComponent;
import cn.cerc.ui.other.BuildText;
import cn.cerc.ui.parts.UIComponent;

public class DateField extends AbstractField
        implements IFieldPattern, IFieldPlaceholder, IFieldRequired, IFieldBuildText {
    private static final ClassConfig config = new ClassConfig(DateField.class, SummerUI.ID);
    private UIDialogField dialog;
    private String pattern;
    private String placeholder;
    private boolean required;
    private boolean autofocus;
    private BuildText buildText;
    private UIComponent helper;

    public DateField(UIComponent owner, String name, String field) {
        super(owner, name, 5);
        this.setField(field);
        this.setDialog("showDateDialog");
        this.setAlign("center");
    }

    public DateField(UIComponent owner, String name, String field, int width) {
        super(owner, name, width);
        this.setField(field);
        this.setDialog("showDateDialog");
        this.setAlign("center");
    }

    @Override
    public FieldTitle createTitle() {
        FieldTitle title = super.createTitle();
        title.setType("date");
        return title;
    }

    @Override
    public String getText() {
        Record record = getRecord();
        if (record == null) {
            return null;
        }
        if (getBuildText() != null) {
            HtmlWriter html = new HtmlWriter();
            getBuildText().outputText(record, html);
            return html.toString();
        }
        if (record.hasValue(getField())) {
            return record.getDate(getField()).getDate();
        } else {
            return "";
        }
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }

    @Override
    public DateField setPattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    @Override
    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    public DateField setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public DateField setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public boolean isAutofocus() {
        return autofocus;
    }

    public DateField setAutofocus(boolean autofocus) {
        this.autofocus = autofocus;
        return this;
    }

    @Override
    public DateField createText(BuildText buildText) {
        this.buildText = buildText;
        return this;
    }

    @Override
    public BuildText getBuildText() {
        return buildText;
    }

    // 隐藏输出
    @Override
    public void outputHidden(HtmlWriter html) {
        html.print("<input");
        html.print(" type=\"hidden\"");
        html.print(" id=\"%s\"", this.getId());
        html.print(" name=\"%s\"", this.getId());
        String value = this.getText();
        if (value != null) {
            html.print(" value=\"%s\"", value);
        }
        html.println("/>");
    }

    @Override
    public void outputLine(HtmlWriter html) {
        if (this.isReadonly()) {
            html.print(this.getName() + "：");
            html.print(this.getText());
        } else {
            html.print("<label for=\"%s\">%s</label>", this.getId(), this.getName() + "：");
            html.print("<input");
            if (getHtmType() != null) {
                html.print(" type=\"%s\"", this.getHtmType());
            } else {
                html.print(" type=\"text\"");
            }
            html.print(" id=\"%s\"", this.getId());
            html.print(" name=\"%s\"", this.getId());
            String value = this.getText();
            if (value != null) {
                html.print(" value=\"%s\"", value);
            }
            if (this.getValue() != null) {
                html.print(" value=\"%s\"", this.getValue());
            }
            if (this.isReadonly()) {
                html.print(" readonly=\"readonly\"");
            }
            if (this.getCssClass() != null) {
                html.print(" class=\"%s\"", this.getCssClass());
            }
            if (this.isAutofocus()) {
                html.print(" autofocus");
            }
            if (this instanceof IFieldRequired) {
                IFieldRequired obj = (IFieldRequired) this;
                if (obj.isRequired()) {
                    html.print(" required");
                }
            }
            if (this instanceof IFieldMultiple) {
                IFieldMultiple obj = (IFieldMultiple) this;
                if (obj.isMultiple()) {
                    html.print(" multiple");
                }
            }
            if (this instanceof IFieldPlaceholder) {
                IFieldPlaceholder obj = (IFieldPlaceholder) this;
                if (obj.getPlaceholder() != null) {
                    html.print(" placeholder=\"%s\"", obj.getPlaceholder());
                }
            }
            if (this instanceof IFieldPattern) {
                IFieldPattern obj = (IFieldPattern) this;
                if (obj.getPattern() != null) {
                    html.print(" pattern=\"%s\"", obj.getPattern());
                }
            }
            if (this instanceof IFieldEvent) {
                IFieldEvent event = (IFieldEvent) this;
                if (event.getOninput() != null) {
                    html.print(" oninput=\"%s\"", event.getOninput());
                }
                if (event.getOnclick() != null) {
                    html.print(" onclick=\"%s\"", event.getOnclick());
                }
            }
            html.println("/>");

            if (this instanceof IFieldShowStar) {
                IFieldShowStar obj = (IFieldShowStar) this;
                if (obj.isShowStar()) {
                    html.println("<font>*</font>");
                }
            }

            html.print("<span>");
            if (helper != null)
                helper.output(html);
            html.println("</span>");
        }
    }

    public UIDialogField getDialog() {
        return dialog;
    }

    public DateField setDialog(String dialogfunc) {
        this.dialog = new UIDialogField(getHelper());
        dialog.setDialogFunc(dialogfunc);
        dialog.setInputId(this.getId());
        dialog.setConfig(config);
        return this;
    }

    @Deprecated
    public DateField setDialog(String dialogfun, String[] params) {
        setDialog(dialogfun);

        for (String string : params) {
            dialog.add(string);
        }

        return this;
    }

    public UIComponent getHelper() {
        if (helper == null)
            helper = new UIOriginComponent(this);
        return helper;
    }

}
