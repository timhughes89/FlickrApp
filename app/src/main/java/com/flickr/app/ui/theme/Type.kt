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
                letterSpacing = 0.025.sp,
                lineHeight = 8.sp
        ),
        displayMedium = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                letterSpacing = 0.031.sp,
                lineHeight = 8.sp
        ),
        displaySmall = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                letterSpacing = 0.042.sp,
                lineHeight = 8.sp
        ),
        headlineLarge = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                letterSpacing = 0.8.sp,
                lineHeight = 8.sp
        ),
        headlineMedium = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                letterSpacing = 0.2.sp,
                lineHeight = 8.sp
        ),
        headlineSmall = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                letterSpacing = 0.0.sp,
                lineHeight = 8.sp
        ),
        titleLarge = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                letterSpacing = 0.0.sp,
                lineHeight = 24.sp
        ),
        titleMedium = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.2.sp,
                lineHeight = 8.sp
        ),
        titleSmall = TextStyle(
                fontFamily = OverpassFont,
                fontSize = 16.sp,
                letterSpacing = 0.sp,
                lineHeight = 6.sp
        ),

        // Default 16sp
        bodyLarge = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.sp,
                lineHeight = 6.sp
        ),
        // Default 14sp
        bodyMedium = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.6.sp,
                lineHeight = 18.sp
        ),
        // Default 12sp
        bodySmall = TextStyle(
                fontFamily = OverpassFont,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.0.sp,
        ),
        // Default 14sp
        labelLarge = TextStyle(
                fontFamily = OverpassFont,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.1.sp,
                lineHeight = 4.sp
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