package com.cursosant.mdcjcm2

import android.widget.Toast
import androidx.annotation.DimenRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Chip
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Slider
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.cursosant.mdcjcm2.ui.theme.MDCJCM2Theme

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun contentPreview() {
    MDCJCM2Theme {
        Content()
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun Content(modifier: Modifier = Modifier) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.common_padding_default),
                    end = dimensionResource(id = R.dimen.common_padding_default),
                    top = dimensionResource(id = R.dimen.common_padding_default)
                )
            ) {
                val (imageCard, btnbuy, btnSkip, tvTitle, tvContent) = createRefs()
                val image = ContextCompat.getDrawable(LocalContext.current, R.mipmap.ic_launcher)
                Image(
                    bitmap = image!!.toBitmap().asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.constrainAs(imageCard) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    })
                Button(onClick = { }, modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.common_padding_min))
                    .constrainAs(btnbuy) {
                        end.linkTo(parent.end)
                        top.linkTo(imageCard.bottom)
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_shop),
                        contentDescription = null
                    )
                    Text(text = stringResource(id = R.string.card_btn_buy))
                }
                TextButton(onClick = { }, modifier = Modifier.constrainAs(btnSkip) {
                    end.linkTo(btnbuy.start)
                    top.linkTo(btnbuy.top)
                    bottom.linkTo(btnbuy.bottom)
                    height = Dimension.fillToConstraints
                }) {
                    Text(text = stringResource(id = R.string.card_btn_skip))
                }
                Text(
                    text = stringResource(id = R.string.card_title),
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.common_padding_default))
                        .constrainAs(tvTitle) {
                            start.linkTo(imageCard.end)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            width = Dimension.fillToConstraints
                        }
                )
                Text(
                    text = stringResource(id = R.string.large_text),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.common_padding_default))
                        .constrainAs(tvContent) {
                            start.linkTo(tvTitle.start)
                            end.linkTo(tvTitle.end)
                            top.linkTo(tvTitle.bottom)
                            bottom.linkTo(imageCard.bottom)
                            width = Dimension.fillToConstraints
                        }
                )
            }
        }
        var colorMain by remember { mutableStateOf(Color.LightGray) }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorMain)
                .padding(8.dp)
        ) {
            Column {
                /*Image(
                    painter = painterResource(id = R.drawable.icon_shop),
                    contentDescription = "",
                    Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.card_img_cover_height))
                        .background(colorResource(id = R.color.teal_200))
                )*/
                var urlValue by remember {
                    mutableStateOf("https://cdn-3.motorsport.com/images/amp/2eAa5WP2/s1000/ott-tanak-martin-jarveoja-m-sp.jpg")
                }
                GlideImage(
                    model = urlValue, contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.card_img_cover_height))
                        .background(colorResource(id = R.color.teal_200)),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = stringResource(id = R.string.title_black_friday),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.common_padding_default)),
                    style = MaterialTheme.typography.h5
                )

                OutlinedTextField(
                    value = urlValue,
                    onValueChange = { urlValue = it },
                    label = { Text(text = stringResource(id = R.string.input_url)) },
                    singleLine = true,
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.common_padding_default),
                            start = dimensionResource(id = R.dimen.common_padding_default),
                            end = dimensionResource(id = R.dimen.common_padding_default)
                        )
                        .fillMaxWidth(),
                    trailingIcon = {
                        if (urlValue.isNotEmpty()) {
                            Icon(imageVector = Icons.Filled.Clear, contentDescription = "Limpiar",
                                modifier = Modifier.clickable { urlValue = "" })
                        }
                    }
                )
                Text(
                    text = stringResource(id = R.string.card_requiered),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.common_padding_default),
                        top = dimensionResource(id = R.dimen.common_padding_micro)
                    )
                )
                var isCheckBox by remember { mutableStateOf(false) }
                var passwordValue by remember { mutableStateOf("") }
                var isPasswordVisible by remember { mutableStateOf(false) }
                var isSwitchChecked by remember { mutableStateOf(true) }
                OutlinedTextField(
                    value = passwordValue,
                    onValueChange = { passwordValue = it },
                    label = { Text(text = stringResource(id = R.string.card_password)) },
                    singleLine = true,
                    enabled = isCheckBox,
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.common_padding_default),
                            start = dimensionResource(id = R.dimen.common_padding_default),
                            end = dimensionResource(id = R.dimen.common_padding_default)
                        )
                        .fillMaxWidth(),
                    trailingIcon = {
                        Icon(painter = if (isPasswordVisible)
                            painterResource(id = R.drawable.icon_visibility) else painterResource(
                            id = R.drawable.icon_visibility_off
                        ),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                isPasswordVisible = !isPasswordVisible
                            }
                        )
                    }
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = isCheckBox, onCheckedChange = { isCheckBox = it })
                    Text(text = stringResource(id = R.string.check_box_title))
                    Spacer(modifier = Modifier.weight(1f, true))
                    Text(text = stringResource(id = R.string.card_hide_fab))
                    Switch(
                        checked = isSwitchChecked, onCheckedChange = { isSwitchChecked = it },
                        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.common_padding_default))
                    )
                }
                val context = LocalContext.current
                var sliderValue by remember { mutableFloatStateOf(6f) }
                Slider(
                    value = sliderValue, onValueChange = {
                        sliderValue = it
                    },
                    onValueChangeFinished = {
                        Toast.makeText(context, "Vol: $sliderValue", Toast.LENGTH_SHORT).show()
                    },
                    valueRange = 0f..10f,
                    steps = 10
                )

                val emailValue by remember {
                    mutableStateOf("oscarbavo@gmail.com")
                }
                var cpVisible by remember {
                    mutableStateOf(true)
                }
                if (cpVisible) {
                    Chip(
                        onClick = {
                            Toast.makeText(context, emailValue, Toast.LENGTH_SHORT).show()
                        }, modifier = Modifier.padding(
                            start = dimensionResource(
                                id = R.dimen.common_padding_default
                            )
                        )
                    ) {
                        Text(text = emailValue)
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null,
                            modifier = Modifier
                                .size(16.dp)
                                .clickable { cpVisible = false })
                    }
                }
                Divider(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = dimensionResource(id = R.dimen.common_padding_middle))
                )


                var colors = listOf("Red", "Blue", "Green")
                SegmentedControl(items = colors,
                    defaultSelectedItemIndex = -1,
                    cornerRadius = 48,
                    color = R.color.purple_500,
                    onItemSelection = {
                        colorMain = when (it) {
                            0 -> Color.Red
                            1 -> Color.Blue
                            else -> Color.Green
                        }
                    })
            }
        }
    }
}