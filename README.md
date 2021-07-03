<h1 align="center">Banner</h1></br

<p align="center">
Banner is an library for ad view  that implements automatic slide effects using ViewPager2.
</p>


<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
</p>

### Dependency Gradle 
Add below codes to your **root** `build.gradle` file (not your module build.gradle file).
```gradle
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

And add a dependency code to your **module**'s `build.gradle` file.
```gradle
dependencies {
  implementation 'com.github.KennethSS:Banner:1.0.1'
}
```



## Usage

```xml
<com.solar.kenneth.banner.BannerView
    android:id="@+id/banner_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:ratio_width="18"
    app:ratio_height="13"
    app:infinity="true"
    app:autoScroll="true"/>
```

```kotlin
bannerView.setBannerViewListener(object: BannerViewListener {
      override fun onBannerBinding(iv: ImageView, banner: Banner) {
        // Something to load for image
      }
    })
```
