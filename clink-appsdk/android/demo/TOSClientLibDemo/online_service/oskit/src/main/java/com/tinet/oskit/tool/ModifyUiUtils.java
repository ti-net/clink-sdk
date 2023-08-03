package com.tinet.oskit.tool;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.manager.TCustomizationUI;
import com.tinet.oslib.OnlineServiceClient;
import com.tinet.oslib.common.OnlineMessageSenderType;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

public class ModifyUiUtils {

    public static TCustomizationUI kitConfigState() {
        if (TOSClientKit.getTOSClientKitConfig() != null) {
            TCustomizationUI customizationUI = TOSClientKit.getTOSClientKitConfig().getTCustomizationUI();
            if (customizationUI != null) {
                return customizationUI;
            }
        }
        return null;
    }

    public static void modifyBubble(Context mContext, int senderType, View textView) {
        if (kitConfigState() != null) {
            TCustomizationUI customizationUI = kitConfigState();
            if (customizationUI.sendBubbleBackground != 0 && customizationUI.receiveBubbleBackground != 0) {
                int background = senderType == OnlineMessageSenderType.VISITOR ? customizationUI.sendBubbleBackground : customizationUI.receiveBubbleBackground;
                if (textView != null)
                    textView.setBackground(ContextCompat.getDrawable(mContext, background));
            } else if (customizationUI.sendBubbleBackgroundColor != 0 && customizationUI.receiveBubbleBackgroundColor != 0) {
                int backgroundColor = senderType == OnlineMessageSenderType.VISITOR ? customizationUI.sendBubbleBackgroundColor : customizationUI.receiveBubbleBackgroundColor;
                if (textView != null) {
                    Drawable drawable = textView.getBackground();
                    DrawableCompat.setTint(drawable, backgroundColor);
                    textView.setBackground(drawable);
                }
            }

        }
    }

    public static void modifySetTextColor(TextView textView, int color) {
        if (color != 0) {
            textView.setTextColor(color);
        }
    }

    public static void modifySetRadius(CardView cardview, int radius) {
        if (cardview != null && radius != 0) {
            cardview.setRadius(OnlineServiceClient.getContext().getResources().getDimension(radius));
            cardview.invalidate();
        }
    }

    public static void modifySetBackground(View view, int backgroundcolor) {
        if (backgroundcolor != 0) {
            Drawable drawable = view.getBackground();
            DrawableCompat.setTint(drawable, backgroundcolor);
            view.setBackground(drawable);
        }
    }
}
