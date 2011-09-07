# simple-emailer

A simple e-mailing client in clojure using the org.apache.commons.mail wrapper.

Make no doubt about it ... this thing rips off everything that is <a href="https://github.com/slagyr/mmemail">mmemail</a>, which is a fine piece of work except for two fine details.  The first is that when using leiningen, there is a security issue (some signed jar thingy).  This isn't a fault of mmemail, but I get tired of dealing with it.  Second, I couldn't, for the life of me, figure out how to add attachments.

So I wrote this library because somehow it seemed lazier than just figured out how to add attachments to mmemail (or, you know, do some social good and add a patch to mmemail that would allow attachments).

## Usage

The same as mmemail, except you can have an :attachments field.  This can either be a string to your attachment location or a sequence of strings.

Other than that, it is pretty crippled compared to the apache commons mail library.

## License

Copyright (C) 2010 FIXME

Distributed under the Eclipse Public License, the same as Clojure.

I'm sure this violates mmemail's license somehow.