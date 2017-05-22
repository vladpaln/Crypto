# Enigma4K
An advanced version of the enigma machine.  
  
![Alt text](/enigma4Kscreenshot.png?raw=true "Enigma4K")  
  
## Features
- word encoding instead of letter encoding
- up to 4,000 rotors
- each rotor will have 46,655 values
- same rotor can be used multiple times
- up to 4,000 plugboards
- each plugboard will have 46,655 values
- no reflector, value can map to itself
- random step size for each rotor
- random direction rotor rotation
- same passphrase & recipient handle can be used for all messages
- random seed generation, Enigma4K will never encrypt a message using the same rotor/plugboard settings
- ability to randomize word directory for increased security

## Usage
- deploy war to app container (e.g.  tomcat)
- run code
- go to http://localhost:8084/enigma
- accepted text characters: A-Z, a-z, 0-9, standard punctuation

### Encrypt Usage
- enter rotor/plugboard count
- enter pass phrase, this can be anything (both parties must have this information)
- enter recipient email/handle, this can be anything recipient specific (both parties must have this information)
- enter text
- hit encrypt button

### Decrypt Usage
- enter rotor/plugboard count (same as used to encrypt message)
- enter pass phrase (same as used to encrypt message)
- enter recipient email/handle (same as used to encrypt message)
- enter encrypted text
- hit decrypt button

## Embedded Usage
```
String password = "some password or pass phrase";
String handle = "recipient email, handle or other identifier";
int roCount = Enigma4K.COUNT_MIN;		// rotor count 97 - 4,000
int pbCount = Enigma4K.COUNT_MIN;		// plugboard count 97 - 500

String 	plainText = "some secret message";

String cryptText = Enigma4K.encryptText(password, handle, roCount, pbCount, null, plainText);
String plainText = Enigma4K.decryptText(password, handle, roCount, pbCount, null, cryptText);
```
Rotor count is only limited by java heap size.


## TODO
- optimize code
- create unit tests
- upgrade to 8K rotors by using shorts (its all about memory usage)
- test hash function (siphash vs java.hashCode collisions)
- randomize hash key
- port to Spring Boot

## Dependencies
- Apache Commons
- siphash
- log4j
- junit

## References
https://en.wikipedia.org/wiki/Enigma_machine  
https://en.wikipedia.org/wiki/Enigma_rotor_details  
https://en.wikipedia.org/wiki/Fialka  
http://www.cryptomuseum.com/crypto/enigma/working.htm


