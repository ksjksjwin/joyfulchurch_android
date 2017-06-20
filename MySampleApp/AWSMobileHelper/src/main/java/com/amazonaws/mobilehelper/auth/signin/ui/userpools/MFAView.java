package com.amazonaws.mobilehelper.auth.signin.ui.userpools;
//
// Copyright 2017 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.18
//

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.amazonaws.mobilehelper.R;
import com.amazonaws.mobilehelper.auth.signin.SignInManager;
import com.amazonaws.mobilehelper.auth.signin.ui.DisplayUtils;
import com.amazonaws.mobilehelper.auth.signin.ui.SplitBackgroundDrawable;
import com.amazonaws.mobilehelper.config.AWSMobileHelperConfiguration;

import static com.amazonaws.mobilehelper.auth.signin.ui.userpools.UserPoolFormConstants.FORM_BUTTON_COLOR;
import static com.amazonaws.mobilehelper.auth.signin.ui.userpools.UserPoolFormConstants.FORM_BUTTON_CORNER_RADIUS;
import static com.amazonaws.mobilehelper.auth.signin.ui.userpools.UserPoolFormConstants.FORM_SIDE_MARGIN_RATIO;
import static com.amazonaws.mobilehelper.auth.signin.ui.userpools.UserPoolFormConstants.MAX_FORM_WIDTH_IN_PIXELS;

/**
 * View for showing MFA confirmation upon sign-in.
 */
public class MFAView extends LinearLayout {
    private FormView mfaForm;
    private EditText mfaCodeEditText;
    private SplitBackgroundDrawable splitBackgroundDrawable;

    public MFAView(Context context) {
        this(context, null);
    }

    public MFAView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MFAView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        final int backgroundColor;
        if (isInEditMode()) {
            backgroundColor = Color.DKGRAY;
        } else {
            final AWSMobileHelperConfiguration helperConfig =
                SignInManager.getInstance().getIdentityManager().getHelperConfiguration();
            backgroundColor = helperConfig.getSignInBackgroundColor(Color.DKGRAY);
        }
        splitBackgroundDrawable = new SplitBackgroundDrawable(0, backgroundColor);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mfaForm = (FormView) findViewById(R.id.mfa_form);

        mfaCodeEditText = mfaForm.addFormField(getContext(),
            InputType.TYPE_CLASS_NUMBER,
            getContext().getString(R.string.forgot_password_input_code_hint));

        setupVerifyButtonColor();
    }

    private void setupVerifyButtonColor() {
        final Button confirmButton = (Button) findViewById(R.id.mfa_button);
        confirmButton.setBackgroundDrawable(
            DisplayUtils.getRoundedRectangleBackground(FORM_BUTTON_CORNER_RADIUS, FORM_BUTTON_COLOR));
        final LayoutParams signUpButtonLayoutParams = (LayoutParams) confirmButton.getLayoutParams();
        signUpButtonLayoutParams.setMargins(
            mfaForm.getFormShadowMargin(),
            signUpButtonLayoutParams.topMargin,
            mfaForm.getFormShadowMargin(),
            signUpButtonLayoutParams.bottomMargin);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int maxWidth = Math.min((int)(parentWidth * FORM_SIDE_MARGIN_RATIO), MAX_FORM_WIDTH_IN_PIXELS);
        super.onMeasure(MeasureSpec.makeMeasureSpec(maxWidth, MeasureSpec.AT_MOST), heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        setupSplitBackground();
    }

    private void setupSplitBackground() {
        splitBackgroundDrawable.setSplitPointDistanceFromTop(mfaForm.getTop()
            + (mfaForm.getMeasuredHeight()/2));
        ((ViewGroup)getParent()).setBackgroundDrawable(splitBackgroundDrawable);
    }

    public String getMFACode() {
        return mfaCodeEditText.getText().toString();
    }
}
