package com.flickr.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.flickr.app.R

private val OverpassFont = FontFamily(
        Font(R.font.overpass_regular, FontWeight.Normal),
        Font(R.font.overpass_semibold, FontWeight.Medium),
        Font(R.font.overpass_black, FontWeight.Bold)
)

val AppTypography = Typography(
        displayLarge = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
        ),
        displayMedium = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
        ),
        displaySmall = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
        ),
        headlineLarge = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
        ),
        headlineMedium = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
        ),
        headlineSmall = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
        ),
        titleLarge = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
        ),
        titleMedium = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Bold,
        ),
        titleSmall = TextStyle(
                fontFamily = OverpassFont,
                fontSize = 16.sp,
        ),
        // Default 16sp
        bodyLarge = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Bold,
        ),
        // Default 14sp
        bodyMedium = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Normal,
        ),
        // Default 12sp
        bodySmall = TextStyle(
                fontFamily = OverpassFont,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
        ),
        // Default 14sp
        labelLarge = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Bold,
        ),
        // Default 12sp
        labelMedium = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Bold
        ),
        // Default 11sp
        labelSmall = TextStyle(
                fontSize = 10.sp,
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Normal
        )
)