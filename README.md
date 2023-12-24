# Nearby articles with MVVM and Dagger Hilt ⌨ 📍 

Project that shows a list of places of interest nearby from a current location or from an specified coordinates.
Consumes the free API of Wikipedia. Immplemented with SOLID principles using DDD (Domain, Driven, Design) architecture, MVVM and Dependency injection.

* Retrofit
* Dagger Hilt
* MVVM, LiveData, Coroutines
* Google location
* Glide for images
* Android Navigation
* Handling permissions

## Getting Started

To run the application in your local machine you need to download this project and execute it in Android Studio or another IDE compatible with Android developments.

### Using the app

On the first run of the app, location permissions will be requested. Places near the current device location will be loaded 📍  
If location is not accepted, the app can only be used by manually entering the desired coordinates, and places near the specified coordinates will be showed 
🗺️  
Clicking on an item of the list will navigate to the detail of it, showing the coordinates and the distance from the current location of device.  
To use all the features of the app is necessary accept the location permissions, otherwise wont`t be possible know the device location.

#### Errors ❌

When an error ocured during the fetching of data a message will  appear on the screen.

#### No data ⚠️

In lot of cases there are not places close of the specified coordinates, then a message will appear on the screen indicating this.

## Architecture ✏
The project follows the Clean DDD architecture using MVVM design pattern, which consists of the following layers:

* ✏ Presentation Layer: Contains the UI components, ViewModels, and XML files.
* ✏ Domain Layer: Contains the business logic and use cases.
* ✏ Data Layer: Contains the repositories, data sources, API communication.


### Folder Structure 📁
The project is structured as follows:

 📁 app/src/main  
&nbsp;&nbsp;📁 java/com/example/myapp  
&nbsp;&nbsp;&nbsp;&nbsp;📁 data -> Contains repository implementations and data sources  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📁 remote repo -> Use for remote data fetch  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📁 repo -> Use repo domain interface  
&nbsp;&nbsp;&nbsp;&nbsp;📁 di -> Contains a module for provide dependencies and inject them   
&nbsp;&nbsp;&nbsp;&nbsp;📁 domain -> Contains use cases and business logic  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📁 models -> Contains model classes  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📁 repo interface -> Interface of domain repository  
&nbsp;&nbsp;&nbsp;&nbsp;📁 ui -> Contains ViewModels and UI components  
&nbsp;&nbsp;&nbsp;&nbsp;📁 utils -> Contains functions and extension functions to use across the app  

## Technologies

* Android Studio → Kotlin ✔
* Kotlin Coroutines ✔
* Retrofit ✔
* Dagger Hilt ✔
* Google Android Location ✔  

### Libraries 📚

* 📚 Kotlin Coroutines - For asynchronous programming
* 📚 Retrofit - For networking and API communication
* 📚 Dagger Hilt - To dependency injection
* 📚 Navigation component - For implement the navigation resource
* 📚 Google Location - For location services of android devices
* 📚 Glide - For handling images

## Dependency injection with Dagger Hilt

It is necessary prepare activities and view models with a tag to be enable to inyect dependencies inside. And the classes that we need inject must be prepared to be injected with an @Inject constructor.  
If we need inject an interface or class of a third part library we will create an object with tag @Module and prepare them in a function with @Provide tag.

* @AndroidEntryPoint -> Tag to indicate activity like entry point of dependencies
* @HiltViewModel -> Indicate view model like entry point of dependencies
* @Inject constructor -> To prepare a normal class to be injected
* Module object:
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; @Module -> Tag to indicate that will provide dependencies from inside of the object  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; @InstallIn(scope of dependencies) -> Between parenthesis indicates the scope of the dependencies
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; @Provides -> Indicates that this function will be provided for the object module
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; @Singleton -> To only create an instance of this type of class

## Api 

* Documentation → [Wikipedia Geosearch](https://www.mediawiki.org/wiki/API:Geosearch)
* Example → [Wikipedia Geosearch example of query](https://en.wikipedia.org/w/api.php?action=query&generator=geosearch
&prop=coordinates%7Cpageimages&ggscoord=39.46975%7C-0.37739)


