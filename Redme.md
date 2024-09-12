[Technical requirements](mid_senior.pdf)

### Remarks
1. Due to lack of time didn't implement caching in DB for working in offline mode. It can be done via Mediator (
   pagination lib)
2. Handling only some of the error in UI (for append only)
3. Didn't find which fields from backend could be nullable, so could be some crash as all DTO classes generated
   automatically and I didn't put ? after each of field. In real app we know which fields could be nullable

### What is not yet implemented but will be
1. DB caching for offline mode
2. Profiling for speedup compose startup
3. Some data/package structure refactoring
4. Unit tests

Sergii Volchenko ([email me](mailto:svolchenko@gmail.com))\
@2024
