import {library} from '@fortawesome/fontawesome-svg-core';

import {faCheck, faEnvelope, faExclamation, faLock, faSearch, faUser,} from '@fortawesome/free-solid-svg-icons';

const compose = (...func) => comp => func.reduceRight((wrapped, f) => f(wrapped), comp);

function initFontAwesomeLibrary() {
  library.add(faEnvelope, faCheck, faLock, faExclamation, faUser, faSearch);
}

const getNewIdGenerator = () => {
  let id = 0;
  return () => {
    id += 1;
    return id;
  };
};

export { compose, initFontAwesomeLibrary, getNewIdGenerator };
