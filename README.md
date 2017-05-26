# Crypto Mk II
An advanced version of the enigma machine.  
  
![Alt text](/screenshot_1.png?raw=true "Enigma4K")  
  
## Features
- word encoding instead of letter encoding
- up to 4,000 rotors
- each rotor will have 46,655 values
- same rotor can be used multiple times
- up to 500 plugboards
- each plugboard will have 46,655 values
- no reflector, value can map to itself
- random step size for each rotor
- random direction rotor rotation
- same passphrase & recipient handle can be used for all messages
- random seed generation, Enigma4K will never encrypt a message using the same settings
- ability to randomize word directory for increased security

## Usage
- explorer: run enigma4K-1.0-SNAPSHOT-jar-with-dependencies.jar
- command line: java -Xmx2g -jar enigma4K-1.0-SNAPSHOT-jar-with-dependencies.jar
- rotor count is limited by java heap size
- accepted text characters: A-Z, a-z, 0-9, standard punctuation

### Encrypt Usage
- enter rotor/plugboard count
- enter pass phrase, this can be anything (both parties must have this information)
- enter recipient email/handle, this can be anything recipient specific (both parties must have this information)
- enter text
- press encrypt button

### Decrypt Usage
- enter rotor/plugboard count (same as used to encrypt message)
- enter pass phrase (same as used to encrypt message)
- enter recipient email/handle (same as used to encrypt message)
- enter encrypted text
- press decrypt button

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


