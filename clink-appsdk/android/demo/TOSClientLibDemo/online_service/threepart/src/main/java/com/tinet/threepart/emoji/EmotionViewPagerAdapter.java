package com.tinet.threepart.emoji;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tinet.threepart.R;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import static com.tinet.threepart.emoji.EmotionLayout.EMOJI_PER_PAGE;
import static com.tinet.threepart.emoji.EmotionLayout.STICKER_PER_PAGE;

/**
 * CSDN_LQR
 * 表情控件的ViewPager适配器(emoji + 贴图)
 */

public class EmotionViewPagerAdapter extends PagerAdapter {

    int mPageCount = 0;
    int mTabPosi = 0;

    private int mEmotionLayoutWidth;
    private int mEmotionLayoutHeight;

    private IEmotionSelectedListener listener;
    EditText mMessageEditText;

    private boolean tinetShowDelete;

    public void attachEditText(EditText messageEditText) {
        mMessageEditText = messageEditText;
    }

    public EmotionViewPagerAdapter(int emotionLayoutWidth, int emotionLayoutHeight, int tabPosi, IEmotionSelectedListener listener,boolean tinetShowDelete) {
        mEmotionLayoutWidth = emotionLayoutWidth;
        mEmotionLayoutHeight = emotionLayoutHeight;
        mTabPosi = tabPosi;
        this.tinetShowDelete = tinetShowDelete;

        if (mTabPosi == 0)
            mPageCount = (int) Math.ceil(EmojiManager.getDisplayCount() / (float) EMOJI_PER_PAGE);
        else
            mPageCount = (int) Math.ceil(StickerManager.getInstance().getStickerCategories().get(mTabPosi - 1).getStickers().size() / (float) STICKER_PER_PAGE);

        this.listener = listener;
    }

    @Override
    public int getCount() {
        return mPageCount == 0 ? 1 : mPageCount;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Context context = container.getContext();
        RelativeLayout rl = new RelativeLayout(context);
        rl.setGravity(Gravity.CENTER);

        GridView gridView = new GridView(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        gridView.setLayoutParams(params);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setGravity(Gravity.CENTER);
        gridView.setVerticalScrollBarEnabled(false);

        gridView.setTag(position);//标记自己是第几页
        if (mTabPosi == 0) {
            gridView.setOnItemClickListener(emojiListener);
            gridView.setAdapter(new EmojiAdapter(context, mEmotionLayoutWidth, mEmotionLayoutHeight, position * EMOJI_PER_PAGE));
            gridView.setNumColumns(EmotionLayout.EMOJI_COLUMNS);
        } else {
            StickerCategory category = StickerManager.getInstance().getCategory(StickerManager.getInstance().getStickerCategories().get(mTabPosi - 1).getName());
            gridView.setOnItemClickListener(stickerListener);
            gridView.setAdapter(new StickerAdapter(context, category, mEmotionLayoutWidth, mEmotionLayoutHeight, position * STICKER_PER_PAGE));
            gridView.setNumColumns(EmotionLayout.STICKER_COLUMNS);
        }

        rl.addView(gridView);
        if(tinetShowDelete) {
            initCustomerView(rl, context);
        }
        container.addView(rl);
        return rl;

    }

    private void initCustomerView(RelativeLayout rl,Context context){
        // : 2022/7/26 添加删除按钮 （固定在表情面板尾部）
        ImageView emojiThumb = new ImageView(context);
        emojiThumb.setBackgroundResource(R.drawable.ti_emoji_del);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LQREmotionKit.dip2px(83), LQREmotionKit.dip2px(90));
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.rightMargin = LQREmotionKit.dip2px(12);
        emojiThumb.setLayoutParams(layoutParams);
        rl.addView(emojiThumb);
        emojiThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onEmojiSelected(EMO_DELETE);
                }
                onEmojiSelected(EMO_DELETE);
            }
        });
    }

    public static final String EMO_DELETE = "/DEL";

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public AdapterView.OnItemClickListener emojiListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String text = EmojiManager.getDisplayText((int) id);
            if (!TextUtils.isEmpty(text)) {
                if (listener != null) {
                    listener.onEmojiSelected(text);
                }
                onEmojiSelected(text);
            }
        }
    };
    public AdapterView.OnItemClickListener stickerListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            StickerCategory category = StickerManager.getInstance().getStickerCategories().get(mTabPosi - 1);
            List<StickerItem> stickers = category.getStickers();
            int index = position + (Integer) parent.getTag() * STICKER_PER_PAGE;

            if (index >= stickers.size()) {
//                Log.i("CSDN_LQR", "index " + index + " larger than size " + stickers.size());
                return;
            }

            if (listener != null) {
                StickerItem sticker = stickers.get(index);
                StickerCategory real = StickerManager.getInstance().getCategory(sticker.getCategory());

                if (real == null) {
                    return;
                }

                listener.onStickerSelected(sticker.getCategory(), sticker.getName(), StickerManager.getInstance().getStickerBitmapPath(sticker.getCategory(), sticker.getName()));
            }
        }
    };

    private void onEmojiSelected(String key) {
        if (mMessageEditText == null)
            return;
        Editable editable = mMessageEditText.getText();
        if (key.equals("/DEL")) {
            mMessageEditText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
        } else {
            int start = mMessageEditText.getSelectionStart();
            int end = mMessageEditText.getSelectionEnd();
            start = (start < 0 ? 0 : start);
            end = (start < 0 ? 0 : end);
            editable.replace(start, end, key);
        }
    }
}
