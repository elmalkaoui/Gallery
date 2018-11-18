Coding Test

A test coding proposed by Télémaque

Overview

The coding  test consists of making an Android Application listing pictures in two form, 
first in list form and second in a carousel form, and a navigation bar to navigate between two view.

Coding context

Development tools

I used Android studio to make that application.

How to compile

Assume you have those dependencies in your gradle if using a gradle project

dependencies {

    implementation fileTree(dir: 'libs', include: ['*.jar'])
    
    implementation 'com.android.support:appcompat-v7:27.1.1'
    
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    
    implementation 'com.android.support:design:27.1.1'
    
    implementation 'com.android.support:recyclerview-v7:27.1.1'
    
    implementation 'com.squareup.retrofit2:retrofit:2.3.0'
    
    implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
    
    implementation 'com.squareup.picasso:picasso:2.5.2'
    
    implementation 'com.intuit.sdp:sdp-android:1.0.6'
    
    implementation 'com.intuit.ssp:ssp-android:1.0.6'
    
    implementation 'com.android.support:support-v4:27.1.1'
    
    implementation 'com.android.support:cardview-v7:27.1.1'
    
    implementation 'com.nineoldandroids:library:2.4.0'
    
    implementation 'com.daimajia.slider:library:1.1.5@aar'
    
    testImplementation 'junit:junit:4.12'
    
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'

}
