package com.example.newappdi.newapp.DI.network;

import com.example.newappdi.NewsApp.Repository.API_Interface;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class Repository_Factory implements Factory<Repository> {
  private final Provider<API_Interface> apiInterfaceProvider;

  public Repository_Factory(Provider<API_Interface> apiInterfaceProvider) {
    this.apiInterfaceProvider = apiInterfaceProvider;
  }

  @Override
  public Repository get() {
    return newInstance(apiInterfaceProvider.get());
  }

  public static Repository_Factory create(Provider<API_Interface> apiInterfaceProvider) {
    return new Repository_Factory(apiInterfaceProvider);
  }

  public static Repository newInstance(API_Interface apiInterface) {
    return new Repository(apiInterface);
  }
}
