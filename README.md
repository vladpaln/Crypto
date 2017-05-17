# Enigma4K
An advanced version of the enigma machine.  
  
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

## Usage
- run code
- go to http://localhost:8084/enigma
- accepted text characters: A-Z, a-z, 0-9, standard punctuation

### Encrypt Usage
- enter pass phrase, this can be anything (both parties must have this information)
- enter recipient email/handle, this can be anything recipient specific (both parties must have this information)
- enter text
- hit encrypt button

### Decrypt Usage
- enter pass phrase (same as used to encrypt message)
- enter recipient email/handle (same as used to encrypt message)
- enter encrypted text
- hit decrypt button

## Embedded Usage
```
String msgID = Enigma4K.genMsgID();
String path = "src/main/resources/directory";
int roCount = Enigma4K.COUNT_MIN;		// rotor count
int pbCount = Enigma4K.COUNT_MIN;		// plugboard count
Enigma4K enigma = new Enigma4K(path, "pass phrase", "handle", msgID, roCount, pbCount);

String 	plainText = "some secret message";
String 	cryptText = enigma.encryptText(plainText);
plainText = enigma.decryptText(cryptText);
```

## TODO
- optimize code
- create unit tests
- upgrade to 8K rotors by using shorts
- add randomize directory feature
- add directory edit page

## Dependencies
- Apache Commons
- slf4j
- siphash
- log4j
- junit

## References
https://en.wikipedia.org/wiki/Enigma_machine  
https://en.wikipedia.org/wiki/Enigma_rotor_details  
https://en.wikipedia.org/wiki/Fialka  
http://www.cryptomuseum.com/crypto/enigma/working.htm


