Horoscope app.

Special attention is paid to the principles of Clean Architecture, OOP, SOLID, DRY, KISS and YAGNI. No private functions, all the fields are private or protected except Entity HoroscopeCache, because Room requires public fields.

Technologies: Retrofit, Room, Hilt, Navigation Graph, Coroutines.
Architecture pattern: MVVM.
Patterns: Data Mapper, Facade.
API: https://aztro.readthedocs.io/en/latest/

Description:
An application where you can find a horoscope for 3 days (yesterday, today, tomorrow) according to the zodiac sign. If the user opens the application for the first time, then a screen with a choice of zodiac sign appears in front of him. The choice of the user is saved and at the next time you will not have to choose the sign again. Data (forecast) come from the server and then it caches. If the user has already opened a "forecast" on any of the days and turns off the Internet, then the data will be displayed anyway, as they are cached. The data is being cached for all zodiac signs and are removed if they are no longer relevant (a new day has come).
