package com.talhakilic.contactsapp.extensions

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneVisualTransformation  : VisualTransformation{
    override fun filter(text: AnnotatedString): TransformedText {

        val trimmed = if (text.text.length >= 10) {
            text.text.substring(0, 10)
        } else {
            text.text
        }

        val formatted = buildString {
            trimmed.forEachIndexed { index, c ->
                append(c)
                when (index) {
                    2, 5, 7 -> append(" ")
                }
            }
        }

        val offsetMapping = object : OffsetMapping {

            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 3 -> offset
                    offset <= 6 -> offset + 1
                    offset <= 8 -> offset + 2
                    offset <= 10 -> offset + 3
                    else -> formatted.length
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 3 -> offset
                    offset <= 7 -> offset - 1
                    offset <= 10 -> offset - 2
                    offset <= 13 -> offset - 3
                    else -> trimmed.length
                }
            }
        }

        return TransformedText(
            AnnotatedString(formatted),
            offsetMapping
        )
    }
}