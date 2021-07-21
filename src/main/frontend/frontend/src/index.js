import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import { render } from 'react-dom';
import { Provider } from 'react-redux';
import { initFontAwesomeLibrary } from './utils';
import { store } from './store';
import ErrorBoundry from './components/Error-boundry';
import App from './App';

initFontAwesomeLibrary();

render(
  <ErrorBoundry>
    <Provider store={store}>
      <Router>
        <App />
      </Router>
    </Provider>
  </ErrorBoundry>,
  document.getElementById('app'),
);
