/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package com.cburch.draw.shapes;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections15.list.UnmodifiableList;

import com.cburch.draw.util.EditableLabel;
import com.cburch.logisim.data.Attribute;
import com.cburch.logisim.data.AttributeOption;
import com.cburch.logisim.data.Attributes;
import static com.cburch.logisim.util.LocaleString.*;

public class DrawAttr {
    public static final Font DEFAULT_FONT
        = new Font("SansSerif", Font.PLAIN, 12);

    public static final AttributeOption ALIGN_LEFT
        = new AttributeOption(Integer.valueOf(EditableLabel.LEFT), getFromLocale("alignStart"));
    public static final AttributeOption ALIGN_CENTER
        = new AttributeOption(Integer.valueOf(EditableLabel.CENTER), getFromLocale("alignMiddle"));
    public static final AttributeOption ALIGN_RIGHT
        = new AttributeOption(Integer.valueOf(EditableLabel.RIGHT), getFromLocale("alignEnd"));

    public static final AttributeOption PAINT_STROKE
        = new AttributeOption("stroke", getFromLocale("paintStroke"));
    public static final AttributeOption PAINT_FILL
        = new AttributeOption("fill", getFromLocale("paintFill"));
    public static final AttributeOption PAINT_STROKE_FILL
        = new AttributeOption("both", getFromLocale("paintBoth"));

    public static final Attribute<Font> FONT
        = Attributes.forFont("font", getFromLocale("attrFont"));
    public static final Attribute<AttributeOption> ALIGNMENT
        = Attributes.forOption("align", getFromLocale("attrAlign"),
            new AttributeOption[] { ALIGN_LEFT, ALIGN_CENTER, ALIGN_RIGHT });
    public static final Attribute<AttributeOption> PAINT_TYPE
        = Attributes.forOption("paintType", getFromLocale("attrPaint"),
            new AttributeOption[] { PAINT_STROKE, PAINT_FILL, PAINT_STROKE_FILL });
    public static final Attribute<Integer> STROKE_WIDTH
        = Attributes.forIntegerRange("stroke-width", getFromLocale("attrStrokeWidth"), 1, 8);
    public static final Attribute<Color> STROKE_COLOR
        = Attributes.forColor("stroke", getFromLocale("attrStroke"));
    public static final Attribute<Color> FILL_COLOR
        = Attributes.forColor("fill", getFromLocale("attrFill"));
    public static final Attribute<Color> TEXT_DEFAULT_FILL
        = Attributes.forColor("fill", getFromLocale("attrFill"));
    public static final Attribute<Integer> CORNER_RADIUS
        = Attributes.forIntegerRange("rx", getFromLocale("attrRx"), 1, 1000);

    // for text
    public static final List<Attribute<?>> ATTRS_TEXT
        = createAttributes(new Attribute[] { FONT, ALIGNMENT, FILL_COLOR });
    // for text tool
    public static final List<Attribute<?>> ATTRS_TEXT_TOOL
        = createAttributes(new Attribute[] { FONT, ALIGNMENT, TEXT_DEFAULT_FILL });
    // for line, polyline
    public static final List<Attribute<?>> ATTRS_STROKE
        = createAttributes(new Attribute[] { STROKE_WIDTH, STROKE_COLOR });

    // attribute lists for rectangle, oval, polygon
    private static final List<Attribute<?>> ATTRS_FILL_STROKE
        = createAttributes(new Attribute[] { PAINT_TYPE,
                STROKE_WIDTH, STROKE_COLOR });
    private static final List<Attribute<?>> ATTRS_FILL_FILL
        = createAttributes(new Attribute[] { PAINT_TYPE, FILL_COLOR });
    private static final List<Attribute<?>> ATTRS_FILL_BOTH
        = createAttributes(new Attribute[] { PAINT_TYPE,
                STROKE_WIDTH, STROKE_COLOR, FILL_COLOR });

    // attribute lists for rounded rectangle
    private static final List<Attribute<?>> ATTRS_RRECT_STROKE
        = createAttributes(new Attribute[] { PAINT_TYPE,
                STROKE_WIDTH, STROKE_COLOR, CORNER_RADIUS });
    private static final List<Attribute<?>> ATTRS_RRECT_FILL
        = createAttributes(new Attribute[] { PAINT_TYPE,
                FILL_COLOR, CORNER_RADIUS });
    private static final List<Attribute<?>> ATTRS_RRECT_BOTH
        = createAttributes(new Attribute[] { PAINT_TYPE,
                STROKE_WIDTH, STROKE_COLOR, FILL_COLOR, CORNER_RADIUS });

    private static List<Attribute<?>> createAttributes(Attribute<?>[] values) {
        return UnmodifiableList.decorate(Arrays.asList(values));
    }

    public static List<Attribute<?>> getFillAttributes(AttributeOption paint) {
        if (paint.equals(PAINT_STROKE)) {
            return ATTRS_FILL_STROKE;
        } else if (paint.equals(PAINT_FILL)) {
            return ATTRS_FILL_FILL;
        } else {
            return ATTRS_FILL_BOTH;
        }
    }

    public static List<Attribute<?>> getRoundRectAttributes(AttributeOption paint) {
        if (paint.equals(PAINT_STROKE)) {
            return ATTRS_RRECT_STROKE;
        } else if (paint.equals(PAINT_FILL)) {
            return ATTRS_RRECT_FILL;
        } else {
            return ATTRS_RRECT_BOTH;
        }
    }
}
