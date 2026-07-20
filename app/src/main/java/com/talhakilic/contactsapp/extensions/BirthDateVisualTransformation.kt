package com.talhakilic.contactsapp.extensions

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class BirthDateVisualTransformation : VisualTransformation{
    override fun filter(text: AnnotatedString): TransformedText {

        val trimmed = if (text.text.length >= 8) {
            text.text.substring(0, 8)
        } else {
            text.text
        }

        val formatted = buildString {
            trimmed.forEachIndexed { index, c ->
                append(c)
                if (index == 1 || index == 3) {
                    append(".")
                }
            }
        }

        val offsetMapping = object : OffsetMapping {

            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 2 -> offset
                    offset <= 4 -> offset + 1
                    offset <= 8 -> offset + 2
                    else -> formatted.length
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 2 -> offset
                    offset <= 5 -> offset - 1
                    offset <= 10 -> offset - 2
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