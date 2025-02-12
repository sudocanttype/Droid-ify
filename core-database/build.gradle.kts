plugins {
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
	id("com.google.devtools.ksp") version Version.ksp
	kotlin("plugin.serialization") version Version.kotlin
	kotlin("kapt")
	id(Hilt.plugin)
}

android {
	compileSdk = Android.compileSdk
	namespace = "com.looker.core_database"
	defaultConfig {
		minSdk = Android.minSdk
		targetSdk = Android.compileSdk

		ksp {
			arg("room.schemaLocation", "$projectDir/schemas")
		}
	}

	buildTypes {
		release {
			isMinifyEnabled = true
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
}

dependencies {
	implementation(project(Modules.coreModel))
	implementation(Core.core)
	api(Kotlin.serialization)
	implementation(Room.roomRuntime)
	implementation(Room.roomKtx)
	ksp(Room.roomCompiler)

	implementation(Hilt.android)
	kapt(Hilt.compiler)
}