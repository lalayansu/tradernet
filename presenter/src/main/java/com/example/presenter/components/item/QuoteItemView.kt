package com.example.presenter.components.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presenter.components.image.LoadableImage
import com.example.presenter.components.text.PercentageChangeText
import com.example.presenter.theme.TradernetTheme

@Composable
fun QuoteItemView(
    modifier: Modifier = Modifier,
    c: String? = null,
    ltr: String? = null,
    name: String? = null,
    pcp: Double? = null,
    percentageChangeText: String? = null,
    changeText: String? = null,
    priceText: String? = null,
    shouldAnimate: Boolean? = false,
    onAnimationComplete: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier.weight(0.5f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LoadableImage(c = c)

                Text(
                    text = c.orEmpty(),
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 1,
                    overflow = Ellipsis,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }

            Text(
                text = "$ltr | $name",
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = Ellipsis,
                color = MaterialTheme.colorScheme.outline,
            )
        }

        Column(
            modifier = Modifier.weight(0.5f),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            PercentageChangeText(
                percentageChangeValue = pcp,
                shouldAnimate = shouldAnimate,
                onAnimationComplete = onAnimationComplete,
                percentageChangeText = percentageChangeText,
            )

            Text(
                text = "$priceText ( $changeText )",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = Icons.Outlined.ArrowForwardIos,
            contentDescription = Icons.Outlined.ArrowForwardIos.name,
            tint = MaterialTheme.colorScheme.outline
        )
    }
}

@Preview
@Composable
fun QuoteItemViewPreview() {
    TradernetTheme {
        Column {
            QuoteItemView(
                c = "AAPL",
                ltr = "NASDAQ",
                name = "Apple Inc.",
                pcp = 1.46,
                percentageChangeText = "+1.45%",
                priceText = "145.00",
                changeText = "0.5",
                shouldAnimate = true
            )

            Divider()

            QuoteItemView(
                c = "GAZP",
                pcp = -1.46,
                shouldAnimate = true,
                percentageChangeText = "-1.45%",
                priceText = "145.00",
                changeText = "0.5",
                ltr = "MCX",
                name = "Gazprom",
            )

            Divider()

            QuoteItemView(
                c = "YNDEX",
                pcp = 1.46,
                shouldAnimate = false,
                percentageChangeText = "+1.45%",
                priceText = "145.00",
                changeText = "0.5",
                ltr = "MCX",
                name = "Yandex",
            )

            Divider()

            QuoteItemView(
                c = "SBER",
                pcp = -1.46,
                shouldAnimate = false,
                percentageChangeText = "-1.45%",
                priceText = "145.00",
                changeText = "0.5",
                ltr = "MCX",
                name = "Sberbank",
            )
        }
    }
}
