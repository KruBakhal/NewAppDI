package com.example.newappdi.newapp.DI.modules;

import android.content.Context;
import com.androiddevs.mvvmnewsapp.db.ArticleDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NetworkingModule_GetRoomDatabaseFactory implements Factory<ArticleDatabase> {
  private final NetworkingModule module;

  private final Provider<Context> contextProvider;

  public NetworkingModule_GetRoomDatabaseFactory(NetworkingModule module,
      Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public ArticleDatabase get() {
    return getRoomDatabase(module, contextProvider.get());
  }

  public static NetworkingModule_GetRoomDatabaseFactory create(NetworkingModule module,
      Provider<Context> contextProvider) {
    return new NetworkingModule_GetRoomDatabaseFactory(module, contextProvider);
  }

  public static ArticleDatabase getRoomDatabase(NetworkingModule instance, Context context) {
    return Preconditions.checkNotNullFromProvides(instance.getRoomDatabase(context));
  }
}
