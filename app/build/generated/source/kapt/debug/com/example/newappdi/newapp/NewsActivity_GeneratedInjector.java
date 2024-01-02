package com.example.newappdi.newapp;

import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.internal.GeneratedEntryPoint;
import javax.annotation.processing.Generated;

@OriginatingElement(
    topLevelClass = NewsActivity.class
)
@GeneratedEntryPoint
@InstallIn(ActivityComponent.class)
@Generated("dagger.hilt.android.processor.internal.androidentrypoint.InjectorEntryPointGenerator")
public interface NewsActivity_GeneratedInjector {
  void injectNewsActivity(NewsActivity newsActivity);
}
