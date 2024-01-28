package dmitriy.losev.ui.views.textfields

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dmitriy.losev.ui.R
import dmitriy.losev.ui.core.COMMENT_FIELD_TEXT
import dmitriy.losev.ui.core.COMMENT_FIELD_TITLE
import dmitriy.losev.ui.core.DISCORD_FIELD_TEXT
import dmitriy.losev.ui.core.EMAIL_FIELD_TEXT
import dmitriy.losev.ui.core.EMAIL_FIELD_TITLE
import dmitriy.losev.ui.core.EMPTY_STRING
import dmitriy.losev.ui.core.PASSWORD_FIELD_HINT
import dmitriy.losev.ui.core.PASSWORD_FIELD_TEXT
import dmitriy.losev.ui.core.PASSWORD_FIELD_TITLE
import dmitriy.losev.ui.core.PASSWORD_VISIBLE
import dmitriy.losev.ui.core.PHONE_FIELD_TEXT
import dmitriy.losev.ui.core.PHONE_FIELD_TITLE
import dmitriy.losev.ui.core.SKYPE_FIELD_TEXT
import dmitriy.losev.ui.core.TEXT_FIELD_HINT
import dmitriy.losev.ui.core.TEXT_FIELD_TEXT
import dmitriy.losev.ui.core.TEXT_FIELD_TITLE
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.theme.LocalTutorsScheduleShapes
import dmitriy.losev.ui.theme.LocalTutorsScheduleTypography
import dmitriy.losev.ui.theme.TutorsScheduleTheme
import dmitriy.losev.ui.theme.colors.Gray3
import dmitriy.losev.ui.views.Title2Text
import dmitriy.losev.ui.views.phone.PhoneVisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    title: String,
    hint: String,
    text: String,
    enabled: Boolean = true,
    leftIcon: @Composable (() -> Unit)? = null,
    rightIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    autoCorrect: Boolean = false,
    textFieldState: TextFieldState = TextFieldState.DEFAULT,
    clearTextFieldState: () -> Unit = { },
    imeAction: ImeAction = ImeAction.Done,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextChanged: (String) -> Unit = { }
) {

    val typography = LocalTutorsScheduleTypography.current
    val colors = LocalTutorsScheduleColors.current
    val shapes = LocalTutorsScheduleShapes.current

    val textStyle = typography.textField

    val textColor = colors.textPrimary
    val textHintColor = colors.textFieldHint
    val textFieldEmptyBackground = colors.textFieldEmptyBackground
    val textFieldNotEmptyBackground = colors.textFieldBackground
    val textFieldCursorColor = colors.textFieldCursor

    val textFieldShape = shapes.textField
    val textFieldBorderShape = shapes.textFieldBorder

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val borderColor by animateColorAsState(
        targetValue = if (isFocused) textFieldState.focusedBorderColor else textFieldState.unfocusedBorderColor,
        animationSpec = spring(),
        label = "BorderColor"
    )

    val textFieldBackground = if (text.isBlank()) textFieldEmptyBackground else textFieldNotEmptyBackground

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val startPadding = if (leftIcon == null) 20.dp else 0.dp
    val endPadding = if (textFieldState.rightIcon == null && rightIcon == null) 20.dp else 0.dp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        Title2Text(text = title)

        Spacer(modifier = Modifier.height(height = 12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 50.dp)
                .border(width = 1.dp, color = borderColor, shape = textFieldBorderShape)
                .background(color = Color.Transparent)
                .padding(all = 2.dp),
            contentAlignment = Alignment.Center
        ) {

            BasicTextField(
                value = text,
                onValueChange = onTextChanged,
                enabled = enabled,
                modifier = Modifier
                    .fillMaxSize()
                    .focusRequester(focusRequester)
                    .onFocusChanged { clearTextFieldState() },
                textStyle = textStyle.copy(color = textColor),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() },
                    onNext = { focusManager.moveFocus(focusDirection = FocusDirection.Next) }
                ),
                keyboardOptions = KeyboardOptions(
                    capitalization = capitalization,
                    keyboardType = keyboardType,
                    autoCorrect = autoCorrect,
                    imeAction = imeAction
                ),
                singleLine = true,
                interactionSource = interactionSource,
                visualTransformation = visualTransformation,
                cursorBrush = SolidColor(value = textFieldCursorColor)
            ) { box ->
                TextFieldDefaults.DecorationBox(
                    value = text,
                    innerTextField = box,
                    placeholder = {
                        Text(text = hint, color = textHintColor, style = textStyle)
                    },
                    leadingIcon = leftIcon,
                    trailingIcon = textFieldState.rightIcon ?: rightIcon,
                    prefix = prefix,
                    enabled = enabled,
                    singleLine = true,
                    shape = textFieldShape,
                    visualTransformation = VisualTransformation.None,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = textFieldBackground,
                        disabledContainerColor = textFieldBackground,
                        focusedContainerColor = textFieldBackground,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        cursorColor = textFieldCursorColor
                    ),
                    interactionSource = interactionSource,
                    contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(top = 0.dp, bottom = 0.dp, start = startPadding, end = endPadding)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentTextField(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    enabled: Boolean = true,
    textFieldState: TextFieldState = TextFieldState.DEFAULT,
    clearTextFieldState: () -> Unit = { },
    imeAction: ImeAction = ImeAction.Done,
    visibleAll: Boolean,
    onVisibleAllChanged: () -> Unit,
    onTextChanged: (String) -> Unit = { }
) {

    val typography = LocalTutorsScheduleTypography.current
    val colors = LocalTutorsScheduleColors.current
    val shapes = LocalTutorsScheduleShapes.current

    val textStyle = typography.text1
    val subTitleStyle = typography.subTitle

    val textColor = colors.textSecondary
    val textHintColor = colors.textFieldHint
    val textFieldEmptyBackground = colors.textFieldEmptyBackground
    val textFieldNotEmptyBackground = colors.textFieldBackground
    val textFieldCursorColor = colors.textFieldCursor

    val textFieldShape = shapes.textField
    val textFieldBorderShape = shapes.textFieldBorder

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val borderColor by animateColorAsState(
        targetValue = if (isFocused) textFieldState.focusedBorderColor else textFieldState.unfocusedBorderColor,
        animationSpec = spring(),
        label = "BorderColor"
    )

    val textFieldBackground = if (text.isBlank()) textFieldEmptyBackground else textFieldNotEmptyBackground

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val modifierBox = if (visibleAll) {
        Modifier.wrapContentHeight()
    } else {
        Modifier
    }

    val modifierTextField = if (visibleAll) {
        Modifier.wrapContentHeight()
    } else {
        Modifier
    }

    val visibleAllText = if (visibleAll) {
        stringResource(id = R.string.visible_all_off)
    } else {
        stringResource(id = R.string.visible_all_on)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(min = 160.dp)
            .padding(horizontal = 16.dp)
            .animateContentSize(animationSpec = spring()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        Title2Text(text = title)

        Spacer(modifier = Modifier.height(height = 12.dp))

        Box(
            modifier = modifierBox
                .fillMaxWidth()
                .border(width = 1.dp, color = borderColor, shape = textFieldBorderShape)
                .background(color = Color.Transparent)
                .padding(all = 2.dp),
            contentAlignment = Alignment.Center
        ) {

            BasicTextField(
                value = text,
                onValueChange = onTextChanged,
                modifier = modifierTextField
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .onFocusChanged { clearTextFieldState() },
                textStyle = textStyle.copy(color = textColor),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() },
                    onNext = { focusManager.moveFocus(focusDirection = FocusDirection.Next) }
                ),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Text,
                    autoCorrect = true,
                    imeAction = imeAction
                ),
                singleLine = false,
                enabled = enabled,
                interactionSource = interactionSource,
                cursorBrush = SolidColor(value = textFieldCursorColor)
            ) { box ->
                TextFieldDefaults.DecorationBox(
                    value = text,
                    innerTextField = box,
                    placeholder = { Text(text = stringResource(id = R.string.enter_text), color = textHintColor, style = textStyle) },
                    enabled = enabled,
                    singleLine = false,
                    shape = textFieldShape,
                    visualTransformation = VisualTransformation.None,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = textFieldBackground,
                        focusedContainerColor = textFieldBackground,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        cursorColor = textFieldCursorColor
                    ),
                    interactionSource = interactionSource,
                    contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(top = 40.dp, bottom = 40.dp, start = 16.dp, end = 16.dp)
                )
            }

            if (text.isNotBlank()) {

                Text(
                    text = visibleAllText,
                    style = subTitleStyle,
                    color = Gray3,
                    modifier = Modifier
                        .padding(start = 14.dp, bottom = 10.dp)
                        .clickable(onClick = onVisibleAllChanged, role = Role.Button)
                        .align(alignment = Alignment.BottomStart)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit
) {

    val typography = LocalTutorsScheduleTypography.current
    val colors = LocalTutorsScheduleColors.current

    val textStyle = typography.textFieldFilter

    val textColor = colors.filterTextColor
    val textFieldBackground = colors.filter
    val textFieldCursorColor = colors.textFieldCursor

    val interactionSource = remember { MutableInteractionSource() }

    val focusManager = LocalFocusManager.current

    BasicTextField(
        value = text,
        onValueChange = onTextChanged,
        modifier = modifier,
        textStyle = textStyle.copy(color = textColor),
        keyboardActions = KeyboardActions(
            onSearch = { focusManager.clearFocus() }
        ),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text,
            autoCorrect = true,
            imeAction = ImeAction.Search
        ),
        singleLine = true,
        cursorBrush = SolidColor(value = textFieldCursorColor)
    ) { box ->
        TextFieldDefaults.DecorationBox(
            value = text,
            innerTextField = box,
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.search), contentDescription = stringResource(id = R.string.search), tint = Color.Unspecified)
            },
            enabled = true,
            singleLine = true,
            shape = RoundedCornerShape(size = 7.dp),
            visualTransformation = VisualTransformation.None,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = textFieldBackground,
                disabledContainerColor = textFieldBackground,
                focusedContainerColor = textFieldBackground,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = textFieldCursorColor
            ),
            interactionSource = interactionSource,
            contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(top = 0.dp, bottom = 0.dp, start = 0.dp, end = 0.dp)
        )
    }
}

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    title: String,
    hint: String,
    password: String,
    passwordVisible: Boolean,
    imeAction: ImeAction = ImeAction.Done,
    onPasswordChanged: (String) -> Unit,
    textFieldState: TextFieldState = TextFieldState.DEFAULT,
    clearTextFieldState: () -> Unit = { },
    onPasswordVisibleChanged: (Boolean) -> Unit
) {

    val visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(mask = '*')

    val iconColor = LocalTutorsScheduleColors.current.iconPrimary

    DefaultTextField(
        modifier = modifier,
        title = title,
        hint = hint,
        text = password,
        imeAction = imeAction,
        keyboardType = KeyboardType.Password,
        visualTransformation = visualTransformation,
        onTextChanged = onPasswordChanged,
        textFieldState = textFieldState,
        clearTextFieldState = clearTextFieldState,
        rightIcon = {
            IconToggleButton(
                checked = passwordVisible,
                onCheckedChange = onPasswordVisibleChanged,
                colors = IconButtonDefaults.iconToggleButtonColors(containerColor = Color.Transparent)
            ) {
                Icon(painter = painterResource(id = R.drawable.visible), contentDescription = stringResource(id = R.string.visible), tint = iconColor)
            }
        }
    )
}

@Composable
fun PhoneTextField(
    modifier: Modifier = Modifier,
    title: String,
    phone: String,
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
    textFieldState: TextFieldState = TextFieldState.DEFAULT,
    clearTextFieldState: () -> Unit = { },
    onPhoneChanged: (String) -> Unit = { },
    rightIcon: (@Composable () -> Unit)? = null
) {
    DefaultTextField(
        modifier = modifier,
        title = title,
        enabled = enabled,
        hint = stringResource(id = R.string.phone_mask),
        text = phone,
        imeAction = imeAction,
        keyboardType = KeyboardType.Phone,
        prefix = { PhoneTextFieldPrefix() },
        visualTransformation = PhoneVisualTransformation(),
        onTextChanged = onPhoneChanged,
        textFieldState = textFieldState,
        rightIcon = rightIcon,
        clearTextFieldState = clearTextFieldState
    )
}

@Composable
fun PhoneTextFieldWithIcon(
    modifier: Modifier = Modifier,
    title: String,
    phone: String,
    imeAction: ImeAction = ImeAction.Done,
    textFieldState: TextFieldState = TextFieldState.DEFAULT,
    clearTextFieldState: () -> Unit = { },
    onPhoneChanged: (String) -> Unit,
    rightIcon: (@Composable () -> Unit)? = null
) {
    DefaultTextField(
        modifier = modifier,
        title = title,
        hint = stringResource(id = R.string.phone_mask),
        text = phone,
        imeAction = imeAction,
        keyboardType = KeyboardType.Phone,
        prefix = { PhoneTextFieldPrefix() },
        visualTransformation = PhoneVisualTransformation(),
        onTextChanged = onPhoneChanged,
        textFieldState = textFieldState,
        rightIcon = rightIcon,
        clearTextFieldState = clearTextFieldState,
        leftIcon = {
            TextFieldIcon(icon = painterResource(id = R.drawable.phone), contextDescription = title)
        }
    )
}

@Composable
fun PhoneTextFieldPrefix() {

    val textFieldTextStyle = LocalTutorsScheduleTypography.current.textField
    val textFieldTextColor = LocalTutorsScheduleColors.current.textPrimary

    Text(text = "+7 ", style = textFieldTextStyle, color = textFieldTextColor)
}

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    title: String,
    email: String,
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
    onEmailChanged: (String) -> Unit = { },
    textFieldState: TextFieldState = TextFieldState.DEFAULT,
    clearTextFieldState: () -> Unit = { },
) {

    DefaultTextField(
        modifier = modifier,
        title = title,
        enabled = enabled,
        hint = stringResource(id = R.string.email),
        text = email,
        imeAction = imeAction,
        keyboardType = KeyboardType.Email,
        onTextChanged = onEmailChanged,
        textFieldState = textFieldState,
        clearTextFieldState = clearTextFieldState
    )
}

@Composable
fun EmailTextFieldWithIcon(
    modifier: Modifier = Modifier,
    title: String,
    email: String,
    imeAction: ImeAction = ImeAction.Done,
    textFieldState: TextFieldState = TextFieldState.DEFAULT,
    clearTextFieldState: () -> Unit = { },
    onEmailChanged: (String) -> Unit
) {

    val emailTitle = stringResource(id = R.string.email)

    DefaultTextField(
        modifier = modifier,
        title = title,
        hint = emailTitle,
        text = email,
        imeAction = imeAction,
        keyboardType = KeyboardType.Email,
        onTextChanged = onEmailChanged,
        textFieldState = textFieldState,
        clearTextFieldState = clearTextFieldState,
        leftIcon = {
            TextFieldIcon(icon = painterResource(id = R.drawable.email), contextDescription = emailTitle)
        }
    )
}

@Composable
fun SkypeTextField(
    modifier: Modifier = Modifier,
    skype: String,
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
    onSkypeChanged: (String) -> Unit = { }
) {

    val skypeTitle = stringResource(id = R.string.skype)

    DefaultTextField(
        modifier = modifier,
        title = skypeTitle,
        hint = skypeTitle,
        enabled = enabled,
        text = skype,
        imeAction = imeAction,
        keyboardType = KeyboardType.Ascii,
        onTextChanged = onSkypeChanged,
        leftIcon = {
            TextFieldIcon(icon = painterResource(id = R.drawable.skype), contextDescription = skypeTitle)
        }
    )
}

@Composable
fun DiscordTextField(
    modifier: Modifier = Modifier,
    discord: String,
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
    onDiscordChanged: (String) -> Unit = { }
) {

    val discordTitle = stringResource(id = R.string.discord)

    DefaultTextField(
        modifier = modifier,
        title = discordTitle,
        hint = discordTitle,
        enabled = enabled,
        text = discord,
        imeAction = imeAction,
        keyboardType = KeyboardType.Ascii,
        onTextChanged = onDiscordChanged,
        leftIcon = {
            TextFieldIcon(icon = painterResource(id = R.drawable.discord), contextDescription = discordTitle)
        }
    )
}

@Composable
fun TextFieldIcon(icon: Painter, contextDescription: String) {

    val color = LocalTutorsScheduleColors.current.iconPrimary

    Box(modifier = Modifier.padding(start = 20.dp, end = 4.dp), contentAlignment = Alignment.Center) {

        Box(modifier = Modifier.size(size = 24.dp), contentAlignment = Alignment.Center) {
            Icon(painter = icon, contentDescription = contextDescription, tint = color)
        }
    }
}

@Preview(group = "DefaultTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun DefaultTextFieldPreviewWithLightThemeAndWithTextAndDefaultState() {

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            DefaultTextField(title = TEXT_FIELD_TITLE, hint = TEXT_FIELD_HINT, text = TEXT_FIELD_TEXT) {

            }
        }
    }
}

@Preview(group = "DefaultTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun DefaultTextFieldPreviewWithLightThemeAndDefaultState() {

    val (text, onTextChanged) = remember { mutableStateOf(value = "") }

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            DefaultTextField(title = TEXT_FIELD_TITLE, hint = TEXT_FIELD_HINT, text = text, onTextChanged = onTextChanged)
        }
    }
}

@Preview(group = "DefaultTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun DefaultTextFieldPreviewWithLightThemeAndWithTextAndErrorState() {

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            DefaultTextField(title = TEXT_FIELD_TITLE, hint = TEXT_FIELD_HINT, text = TEXT_FIELD_TEXT, textFieldState = TextFieldState.ERROR) {

            }
        }
    }
}

@Preview(group = "DefaultTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun DefaultTextFieldPreviewWithLightThemeAndErrorState() {

    val (text, onTextChanged) = remember { mutableStateOf(value = "") }

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            DefaultTextField(
                title = TEXT_FIELD_TITLE,
                hint = TEXT_FIELD_HINT,
                text = text,
                onTextChanged = onTextChanged,
                textFieldState = TextFieldState.ERROR
            )
        }
    }
}

@Preview(group = "DefaultTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun DefaultTextFieldPreviewWithLightThemeAndWithTextAndWarningState() {

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            DefaultTextField(title = TEXT_FIELD_TITLE, hint = TEXT_FIELD_HINT, text = TEXT_FIELD_TEXT, textFieldState = TextFieldState.WARNING) {

            }
        }
    }
}

@Preview(group = "DefaultTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun DefaultTextFieldPreviewWithLightThemeAndWarningState() {

    val (text, onTextChanged) = remember { mutableStateOf(value = "") }

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            DefaultTextField(
                title = TEXT_FIELD_TITLE,
                hint = TEXT_FIELD_HINT,
                text = text,
                onTextChanged = onTextChanged,
                textFieldState = TextFieldState.WARNING
            )
        }
    }
}

@Preview(group = "DefaultTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun DefaultTextFieldPreviewWithLightThemeAndWithTextAndOkState() {

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            DefaultTextField(title = TEXT_FIELD_TITLE, hint = TEXT_FIELD_HINT, text = TEXT_FIELD_TEXT, textFieldState = TextFieldState.OK) {

            }
        }
    }
}

@Preview(group = "DefaultTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun DefaultTextFieldPreviewWithLightThemeAndOkState() {

    val (text, onTextChanged) = remember { mutableStateOf(value = "") }

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            DefaultTextField(
                title = TEXT_FIELD_TITLE,
                hint = TEXT_FIELD_HINT,
                text = text,
                onTextChanged = onTextChanged,
                textFieldState = TextFieldState.OK
            )
        }
    }
}

@Preview(group = "DefaultTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun DefaultTextFieldPreviewWithDarkTheme() {

    val (text, onTextChanged) = remember { mutableStateOf(value = "") }

    TutorsScheduleTheme(darkTheme = true) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            DefaultTextField(title = TEXT_FIELD_TITLE, hint = TEXT_FIELD_HINT, text = text, onTextChanged = onTextChanged)
        }
    }
}

@Preview(group = "CommentTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun CommentTextFieldPreviewWithLightTheme() {

    val (text, onTextChanged) = remember { mutableStateOf(value = EMPTY_STRING) }
    val (visibleAll, onVisibleAllChanged) = remember { mutableStateOf(value = false) }

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 500.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            CommentTextField(title = COMMENT_FIELD_TITLE, text = text, onTextChanged = onTextChanged, visibleAll = visibleAll, onVisibleAllChanged = {
                onVisibleAllChanged(visibleAll.not())
            })
        }
    }
}

@Preview(group = "CommentTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun CommentTextFieldPreviewWithLightThemeAndWithData() {

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 500.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            CommentTextField(title = COMMENT_FIELD_TITLE, text = COMMENT_FIELD_TEXT, visibleAll = false, onVisibleAllChanged = { }) {

            }
        }
    }
}

@Preview(group = "EmailTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun EmailTextFieldPreviewWithLightTheme() {

    val (email, onEmailChanged) = remember { mutableStateOf(value = "") }

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            EmailTextField(title = EMAIL_FIELD_TITLE, email = email, onEmailChanged = onEmailChanged)
        }
    }
}

@Preview(group = "EmailTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun EmailTextFieldPreviewWithLightThemeAndWithData() {

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            EmailTextField(title = EMAIL_FIELD_TITLE, email = EMAIL_FIELD_TEXT, onEmailChanged = { })
        }
    }
}

@Preview(group = "EmailTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun EmailTextFieldPreviewWithDarkTheme() {

    val (email, onEmailChanged) = remember { mutableStateOf(value = "") }

    TutorsScheduleTheme(darkTheme = true) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            EmailTextField(title = EMAIL_FIELD_TITLE, email = email, onEmailChanged = onEmailChanged)
        }
    }
}

@Preview(group = "EmailTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun EmailTextFieldPreviewWithDarkThemeAndWithData() {

    TutorsScheduleTheme(darkTheme = true) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            EmailTextField(title = EMAIL_FIELD_TITLE, email = EMAIL_FIELD_TEXT, onEmailChanged = { })
        }
    }
}

@Preview(group = "EmailTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun EmailTextFieldWithIconPreviewWithLightTheme() {

    val (email, onEmailChanged) = remember { mutableStateOf(value = "") }

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            EmailTextFieldWithIcon(title = EMAIL_FIELD_TITLE, email = email, onEmailChanged = onEmailChanged)
        }
    }
}

@Preview(group = "EmailTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun EmailTextFieldWithIconPreviewWithLightThemeAndWithData() {

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            EmailTextFieldWithIcon(title = EMAIL_FIELD_TITLE, email = EMAIL_FIELD_TEXT) {

            }
        }
    }
}

@Preview(group = "EmailTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun EmailTextFieldWithIconPreviewWithDarkTheme() {

    val (email, onEmailChanged) = remember { mutableStateOf(value = "") }

    TutorsScheduleTheme(darkTheme = true) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            EmailTextFieldWithIcon(title = EMAIL_FIELD_TITLE, email = email, onEmailChanged = onEmailChanged)
        }
    }
}

@Preview(group = "EmailTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun EmailTextFieldWithIconPreviewWithDarkThemeAndWithData() {

    TutorsScheduleTheme(darkTheme = true) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            EmailTextFieldWithIcon(title = EMAIL_FIELD_TITLE, email = EMAIL_FIELD_TEXT) {

            }
        }
    }
}

@Preview(group = "SkypeTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun SkypeTextFieldPreviewWithLightTheme() {

    val (skype, onSkypeChanged) = remember { mutableStateOf(value = "") }

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            SkypeTextField(skype = skype, onSkypeChanged = onSkypeChanged)
        }
    }
}

@Preview(group = "SkypeTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun SkypeTextFieldPreviewWithLightThemeAndWithData() {

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            SkypeTextField(skype = SKYPE_FIELD_TEXT) {

            }
        }
    }
}

@Preview(group = "SkypeTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun SkypeTextFieldPreviewWithDarkTheme() {

    val (skype, onSkypeChanged) = remember { mutableStateOf(value = "") }

    TutorsScheduleTheme(darkTheme = true) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            SkypeTextField(skype = skype, onSkypeChanged = onSkypeChanged)
        }
    }
}

@Preview(group = "SkypeTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun SkypeTextFieldPreviewWithDarkThemeAndWithData() {

    TutorsScheduleTheme(darkTheme = true) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            SkypeTextField(skype = SKYPE_FIELD_TEXT) {

            }
        }
    }
}

@Preview(group = "DiscordTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun DiscordTextFieldPreviewWithLightTheme() {

    val (discord, onDiscordChanged) = remember { mutableStateOf(value = "") }

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            DiscordTextField(discord = discord, onDiscordChanged = onDiscordChanged)
        }
    }
}

@Preview(group = "DiscordTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun DiscordTextFieldPreviewWithLightThemeAndWithData() {

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            DiscordTextField(discord = DISCORD_FIELD_TEXT) {

            }
        }
    }
}

@Preview(group = "DiscordTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun DiscordTextFieldPreviewWithDarkTheme() {

    val (discord, onDiscordChanged) = remember { mutableStateOf(value = "") }

    TutorsScheduleTheme(darkTheme = true) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            DiscordTextField(discord = discord, onDiscordChanged = onDiscordChanged)
        }
    }
}

@Preview(group = "DiscordTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun DiscordTextFieldPreviewWithDarkThemeAndWithData() {

    TutorsScheduleTheme(darkTheme = true) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            DiscordTextField(discord = DISCORD_FIELD_TEXT) {

            }
        }
    }
}

@Preview(group = "PasswordTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun PasswordTextFieldPreviewWithLightTheme() {

    val (password, onPasswordChanged) = remember { mutableStateOf(value = "") }
    val (passwordVisible, onPasswordVisibleChanged) = remember { mutableStateOf(value = false) }

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            PasswordTextField(
                title = PASSWORD_FIELD_TITLE,
                hint = PASSWORD_FIELD_HINT,
                password = password,
                passwordVisible = passwordVisible,
                onPasswordChanged = onPasswordChanged,
                onPasswordVisibleChanged = onPasswordVisibleChanged
            )
        }
    }
}

@Preview(group = "PasswordTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun PasswordTextFieldPreviewWithLightThemeAndWithData() {

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            PasswordTextField(
                title = PASSWORD_FIELD_TITLE,
                hint = PASSWORD_FIELD_HINT,
                password = PASSWORD_FIELD_TEXT,
                passwordVisible = PASSWORD_VISIBLE,
                onPasswordChanged = { },
                onPasswordVisibleChanged = { })
        }
    }
}

@Preview(group = "PasswordTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun PasswordTextFieldPreviewWithDarkTheme() {

    val (password, onPasswordChanged) = remember { mutableStateOf(value = "") }
    val (passwordVisible, onPasswordVisibleChanged) = remember { mutableStateOf(value = false) }

    TutorsScheduleTheme(darkTheme = true) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            PasswordTextField(
                title = PASSWORD_FIELD_TITLE,
                hint = PASSWORD_FIELD_HINT,
                password = password,
                passwordVisible = passwordVisible,
                onPasswordChanged = onPasswordChanged,
                onPasswordVisibleChanged = onPasswordVisibleChanged
            )
        }
    }
}

@Preview(group = "PasswordTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun PasswordTextFieldPreviewWithDarkThemeAndWithData() {

    TutorsScheduleTheme(darkTheme = true) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            PasswordTextField(
                title = PASSWORD_FIELD_TITLE,
                hint = PASSWORD_FIELD_HINT,
                password = PASSWORD_FIELD_TEXT,
                passwordVisible = PASSWORD_VISIBLE,
                onPasswordChanged = { },
                onPasswordVisibleChanged = { })
        }
    }
}

@Preview(group = "PhoneTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun PhoneTextFieldPreviewWithLightTheme() {

    val (phone, onPhoneChanged) = remember { mutableStateOf(value = "") }

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            PhoneTextField(title = PHONE_FIELD_TITLE, phone = phone, onPhoneChanged = onPhoneChanged)
        }
    }
}

@Preview(group = "PhoneTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun PhoneTextFieldPreviewWithLightThemeAndWithData() {

    TutorsScheduleTheme(darkTheme = false) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            PhoneTextField(title = PHONE_FIELD_TITLE, phone = PHONE_FIELD_TEXT, onPhoneChanged = { })
        }
    }
}

@Preview(group = "PhoneTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun PhoneTextFieldPreviewWithDarkTheme() {

    val (phone, onPhoneChanged) = remember { mutableStateOf(value = "") }

    TutorsScheduleTheme(darkTheme = true) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            PhoneTextField(title = PHONE_FIELD_TITLE, phone = phone, onPhoneChanged = onPhoneChanged)
        }
    }
}

@Preview(group = "PhoneTextField", backgroundColor = 0xFFFFFFFF)
@Composable
private fun PhoneTextFieldPreviewWithDarkThemeAndWithData() {

    TutorsScheduleTheme(darkTheme = true) {

        val colors = LocalTutorsScheduleColors.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 156.dp)
                .background(color = colors.backgroundPrimary), contentAlignment = Alignment.Center
        ) {

            PhoneTextField(title = PHONE_FIELD_TITLE, phone = PHONE_FIELD_TEXT, onPhoneChanged = { })
        }
    }
}