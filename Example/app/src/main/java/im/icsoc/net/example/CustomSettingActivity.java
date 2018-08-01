package im.icsoc.net.example;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import net.icsoc.im.core.api.ZTIMCore;
import net.icsoc.im.imkit.api.IMKitCore;
import net.icsoc.im.imkit.bean.UICustomization;
import net.icsoc.im.imkit.bean.ZTIMOptions;
import net.icsoc.im.imkit.helper.ZTOptionsHelper;

public class CustomSettingActivity extends Activity implements View.OnClickListener {

    private Switch leftAvatarSwitch, rightAvatarSwitch, avatarShapeSwitch,
            headerTitleSwitch, headerIconSwitch, emojiSwitch, audioSwtich,
            cameraSwitch, gallerySwitch, softInputSwitch;

    View msgListBg, tipsTextColor, leftMsgTextColor, rightMsgTextColor, leftContainerBg, rightContainerBg, headerBg;
    TextView tipsTextSize, dividerHeight, msgTextSize, headerTitleValue;
    ImageView headerIcon;

    UICustomization uiOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_setting);
        ZTIMOptions chatOptions = ZTOptionsHelper.getInstance().getChatOptions(this);
        if (chatOptions == null || chatOptions.uiCustomization == null) {
            uiOptions = new UICustomization();
        } else {
            uiOptions = ZTOptionsHelper.getInstance().getChatOptions(this).uiCustomization;
        }


        initListener();
    }

    private void initListener() {
        msgListBg = findViewById(R.id.msg_list_bg);
        tipsTextColor = findViewById(R.id.tip_text_color);
        tipsTextSize = findViewById(R.id.tip_text_size);
        leftContainerBg = findViewById(R.id.left_container_bg);
        rightContainerBg = findViewById(R.id.right_container_bg);
        leftMsgTextColor = findViewById(R.id.left_msg_text_color);
        rightMsgTextColor = findViewById(R.id.right_msg_text_color);
        msgTextSize = findViewById(R.id.msg_text_size);
        headerBg = findViewById(R.id.header_bg);

        headerTitleValue = findViewById(R.id.header_title_value);
        headerIcon = findViewById(R.id.header_icon);

        dividerHeight = findViewById(R.id.divider_height);


        leftAvatarSwitch = findViewById(R.id.switch_left_avatar);
        rightAvatarSwitch = findViewById(R.id.switch_right_avatar);
        avatarShapeSwitch = findViewById(R.id.switch_avatar_shape);
        softInputSwitch = findViewById(R.id.switch_input_method);
        audioSwtich = findViewById(R.id.switch_audio);
        emojiSwitch = findViewById(R.id.switch_emoji);
        cameraSwitch = findViewById(R.id.switch_camera);
        gallerySwitch = findViewById(R.id.switch_gallery);

        headerTitleSwitch = findViewById(R.id.switch_header_title);
        headerIconSwitch = findViewById(R.id.switch_header_icon);

        leftAvatarSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                uiOptions.hideLeftAvatar = b;
            }
        });

        rightAvatarSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                uiOptions.hideRightAvatar = b;
            }
        });

        avatarShapeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                uiOptions.avatarShape = b ? 1 : 0;
            }
        });

        softInputSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                uiOptions.hideKeyboardOnEnterConsult = b;
            }
        });

        audioSwtich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                uiOptions.hideAudio = b;
            }
        });

        emojiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                uiOptions.hideEmoji = b;
            }
        });

        cameraSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                uiOptions.hideSendPictureButton = b;
            }
        });

        gallerySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                uiOptions.hidePhotographButton = b;
            }
        });

        headerTitleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                uiOptions.hideHeaderTitle = b;
            }
        });

        headerIconSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                uiOptions.hideHeaderIcon = b;
            }
        });


        findViewById(R.id.btn).setOnClickListener(this);
        findViewById(R.id.config_msg_list_bg).setOnClickListener(this);
        findViewById(R.id.config_tip_text_color).setOnClickListener(this);
        findViewById(R.id.config_tip_text_size).setOnClickListener(this);
        findViewById(R.id.config_left_container_bg).setOnClickListener(this);
        findViewById(R.id.config_right_container_bg).setOnClickListener(this);
        findViewById(R.id.config_left_msg_text_color).setOnClickListener(this);
        findViewById(R.id.config_right_msg_text_color).setOnClickListener(this);
        findViewById(R.id.config_msg_text_size).setOnClickListener(this);

        findViewById(R.id.config_header_background).setOnClickListener(this);
        findViewById(R.id.config_header_title).setOnClickListener(this);
        findViewById(R.id.config_header_icon).setOnClickListener(this);

        findViewById(R.id.config_divider_height).setOnClickListener(this);

        initView();
    }

    private void initView() {
        if (uiOptions.msgBackgroundUri != 0)
            msgListBg.setBackgroundResource(uiOptions.msgBackgroundUri);
        else if (uiOptions.msgBackgroundColor != 0) {
            msgListBg.setBackgroundColor(uiOptions.msgBackgroundColor);
        }

        if (uiOptions.tipsTextColor != 0) {
            tipsTextColor.setBackgroundColor(uiOptions.tipsTextColor);
        }
        if (uiOptions.tipsTextSize != 0) {
            tipsTextSize.setText(String.valueOf(uiOptions.tipsTextSize));
        }

        if (uiOptions.textMsgColorLeft != 0) {
            leftMsgTextColor.setBackgroundColor(uiOptions.textMsgColorLeft);
        }
        if (uiOptions.textMsgColorRight != 0) {
            rightMsgTextColor.setBackgroundColor(uiOptions.textMsgColorRight);
        }
        if (uiOptions.textMsgSize != 0) {
            msgTextSize.setText(String.valueOf(uiOptions.textMsgSize));
        }
        if (uiOptions.msgListViewDividerHeight != 0) {
            dividerHeight.setText(String.valueOf(uiOptions.msgListViewDividerHeight));
        }

        leftAvatarSwitch.setChecked(uiOptions.hideLeftAvatar);
        rightAvatarSwitch.setChecked(uiOptions.hideRightAvatar);
        avatarShapeSwitch.setChecked(uiOptions.avatarShape == 1);
        headerTitleSwitch.setChecked(uiOptions.hideHeaderTitle);
        headerIconSwitch.setChecked(uiOptions.hideHeaderIcon);
        softInputSwitch.setChecked(uiOptions.hideKeyboardOnEnterConsult);
        audioSwtich.setChecked(uiOptions.hideAudio);
        emojiSwitch.setChecked(uiOptions.hideEmoji);
        cameraSwitch.setChecked(uiOptions.hidePhotographButton);
        gallerySwitch.setChecked(uiOptions.hideSendPictureButton);

        if (uiOptions.titleBackgroundResId != 0) {
            headerBg.setBackgroundResource(uiOptions.titleBackgroundResId);
        } else if (uiOptions.titleBackgroundColor != 0) {
            headerBg.setBackgroundResource(uiOptions.titleBackgroundColor);
        }

        if (uiOptions.msgItemBackgroundLeft != 0) {
            leftContainerBg.setBackgroundResource(uiOptions.msgItemBackgroundLeft);
        }
        if (uiOptions.msgItemBackgroundRight != 0) {
            rightContainerBg.setBackgroundResource(uiOptions.msgItemBackgroundRight);
        }

        if (!TextUtils.isEmpty(uiOptions.headerTitle)) {
            headerTitleValue.setText(uiOptions.headerTitle);
        }
        if (uiOptions.headerIcon != 0) {
            headerIcon.setImageResource(uiOptions.headerIcon);
        }
    }


    private void saveConfigInfo() {
        ZTIMOptions options = new ZTIMOptions();
        options.uiCustomization = uiOptions;
        IMKitCore.initOptions(this, options);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                saveConfigInfo();
                finish();
                break;

            case R.id.config_msg_list_bg:
                new ActionSheetDialog(this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItems("蓝色", "灰色", "自定义")
                        .setOnSheetItemClickListener(new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which, View V) {
                                switch (which) {
                                    case 0:
                                        uiOptions.msgBackgroundColor = Color.BLUE;
                                        uiOptions.msgBackgroundUri = 0;
                                        msgListBg.setBackgroundColor(Color.BLUE);
                                        break;
                                    case 1:
                                        uiOptions.msgBackgroundColor = Color.GRAY;
                                        uiOptions.msgBackgroundUri = 0;
                                        msgListBg.setBackgroundColor(Color.GRAY);
                                        break;
                                    case 2:
                                        uiOptions.msgBackgroundUri = R.mipmap.msg_bg;
                                        msgListBg.setBackgroundResource(R.mipmap.msg_bg);
                                        break;

                                }
                            }
                        })
                        .show();
                break;

            case R.id.config_tip_text_color:
                new ActionSheetDialog(this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItems("蓝色", "灰色", "红色")
                        .setOnSheetItemClickListener(new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which, View V) {
                                switch (which) {
                                    case 0:
                                        uiOptions.tipsTextColor = Color.BLUE;
                                        tipsTextColor.setBackgroundColor(Color.BLUE);
                                        break;
                                    case 1:
                                        uiOptions.tipsTextColor = Color.GRAY;
                                        tipsTextColor.setBackgroundColor(Color.GRAY);
                                        break;
                                    case 2:
                                        uiOptions.tipsTextColor = Color.RED;
                                        tipsTextColor.setBackgroundColor(Color.RED);
                                        break;

                                }
                            }
                        })
                        .show();
                break;

            case R.id.config_tip_text_size:
                new ActionSheetDialog(this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItems("10", "12", "14")
                        .setOnSheetItemClickListener(new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which, View V) {
                                switch (which) {
                                    case 0:
                                        uiOptions.tipsTextSize = 10;
                                        tipsTextSize.setText("10");
                                        break;
                                    case 1:
                                        uiOptions.tipsTextSize = 12;
                                        tipsTextSize.setText("12");
                                        break;
                                    case 2:
                                        uiOptions.tipsTextSize = 14;
                                        tipsTextSize.setText("14");
                                        break;

                                }
                            }
                        })
                        .show();
                break;

            case R.id.config_left_container_bg:
                new ActionSheetDialog(this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItems("蓝色", "默认")
                        .setOnSheetItemClickListener(new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which, View V) {
                                switch (which) {
                                    case 0:
                                        uiOptions.msgItemBackgroundLeft = R.drawable.home01_bg_card;
                                        leftContainerBg.setBackgroundResource(R.drawable.home01_bg_card);
                                        break;
                                    case 1:
                                        uiOptions.msgItemBackgroundLeft = R.drawable.chat_from_normal_bg;
                                        leftContainerBg.setBackgroundResource(R.drawable.chat_from_normal_bg);
                                        break;

                                }
                            }
                        })
                        .show();
                break;

            case R.id.config_right_container_bg:
                new ActionSheetDialog(this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItems("红色", "蓝色")
                        .setOnSheetItemClickListener(new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which, View V) {
                                switch (which) {
                                    case 0:
                                        uiOptions.msgItemBackgroundRight = R.drawable.my_message_right_bg;
                                        rightContainerBg.setBackgroundResource(R.drawable.my_message_right_bg);
                                        break;
                                    case 1:
                                        uiOptions.msgItemBackgroundRight = R.drawable.chat_to_normal_bg;
                                        rightContainerBg.setBackgroundResource(R.drawable.chat_to_normal_bg);
                                        break;
                                }
                            }
                        })
                        .show();
                break;

            case R.id.config_left_msg_text_color:
                new ActionSheetDialog(this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItems("蓝色", "灰色", "红色")
                        .setOnSheetItemClickListener(new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which, View V) {
                                switch (which) {
                                    case 0:
                                        uiOptions.textMsgColorLeft = Color.BLUE;
                                        leftMsgTextColor.setBackgroundColor(Color.BLUE);
                                        break;
                                    case 1:
                                        uiOptions.textMsgColorLeft = Color.GRAY;
                                        leftMsgTextColor.setBackgroundColor(Color.GRAY);
                                        break;
                                    case 2:
                                        uiOptions.textMsgColorLeft = Color.RED;
                                        leftMsgTextColor.setBackgroundColor(Color.RED);
                                        break;

                                }
                            }
                        })
                        .show();
                break;

            case R.id.config_right_msg_text_color:
                new ActionSheetDialog(this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItems("红色", "黑色", "黄色")
                        .setOnSheetItemClickListener(new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which, View V) {
                                switch (which) {
                                    case 0:
                                        uiOptions.textMsgColorRight = Color.RED;
                                        rightMsgTextColor.setBackgroundColor(Color.RED);
                                        break;
                                    case 1:
                                        uiOptions.textMsgColorRight = Color.BLACK;
                                        rightMsgTextColor.setBackgroundColor(Color.BLACK);
                                        break;
                                    case 2:
                                        uiOptions.textMsgColorRight = Color.YELLOW;
                                        rightMsgTextColor.setBackgroundColor(Color.YELLOW);
                                        break;
                                }
                            }
                        })
                        .show();
                break;

            case R.id.config_msg_text_size:
                new ActionSheetDialog(this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItems("14", "16", "18")
                        .setOnSheetItemClickListener(new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which, View V) {
                                switch (which) {
                                    case 0:
                                        uiOptions.textMsgSize = 14;
                                        msgTextSize.setText("14");
                                        break;
                                    case 1:
                                        uiOptions.textMsgSize = 16;
                                        msgTextSize.setText("16");
                                        break;
                                    case 2:
                                        uiOptions.textMsgSize = 18;
                                        msgTextSize.setText("18");
                                        break;

                                }
                            }
                        })
                        .show();
                break;

            case R.id.config_header_background:
                new ActionSheetDialog(this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItems("drawable值", "纯色(灰色)")
                        .setOnSheetItemClickListener(new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which, View V) {
                                switch (which) {
                                    case 0:
                                        uiOptions.titleBackgroundResId = R.drawable.background_call_header;
                                        headerBg.setBackgroundResource(R.drawable.background_call_header);
                                        break;
                                    case 1:
                                        uiOptions.titleBackgroundColor = R.color.txt_common;
                                        headerBg.setBackgroundColor(getResources().getColor(R.color.txt_common));
                                        break;
                                }
                            }
                        })
                        .show();
                break;

            case R.id.config_header_title:
                new ActionSheetDialog(this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItems("企业名称", "坐席昵称")
                        .setOnSheetItemClickListener(new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which, View V) {
                                switch (which) {
                                    case 0:
                                        uiOptions.headerTitle = "中通天鸿";
                                        headerTitleValue.setText("中通天鸿");
                                        break;
                                    case 1:
                                        uiOptions.headerTitle = "";
                                        headerTitleValue.setText("坐席昵称");
                                        break;
                                }
                            }
                        })
                        .show();
                break;

            case R.id.config_header_icon:
                new ActionSheetDialog(this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItems("企业logo", "坐席头像")
                        .setOnSheetItemClickListener(new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which, View V) {
                                switch (which) {
                                    case 0:
                                        uiOptions.headerIcon = R.mipmap.push;
                                        headerIcon.setImageResource(R.mipmap.push);
                                        break;
                                    case 1:
                                        uiOptions.headerIcon = 0;
                                        break;
                                }
                            }
                        })
                        .show();
                break;

            case R.id.config_divider_height:
                new ActionSheetDialog(this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItems("10", "20", "30")
                        .setOnSheetItemClickListener(new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which, View V) {
                                switch (which) {
                                    case 0:
                                        uiOptions.msgListViewDividerHeight = 10;
                                        dividerHeight.setText("10");
                                        break;
                                    case 1:
                                        uiOptions.msgListViewDividerHeight = 20;
                                        dividerHeight.setText("20");
                                        break;
                                    case 2:
                                        uiOptions.msgListViewDividerHeight = 30;
                                        dividerHeight.setText("30");
                                        break;

                                }
                            }
                        })
                        .show();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ZTIMCore.userTrackHistory("CustomSettingActivity", true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ZTIMCore.userTrackHistory("CustomSettingActivity", false);
    }
}
