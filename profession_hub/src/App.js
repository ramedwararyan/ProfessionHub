// App.js
import React from 'react';
import LoginForm from './components/LoginForm'
import LandingPage from './components/LandingPage';
import  {BrowserRouter , Routes,Route} from 'react-router-dom'

const App = () => {
  return (
    <div>
      <BrowserRouter>
      <Routes>
        <Route path='/home' element={<LandingPage/>} />
        <Route path='/login' element={<LoginForm/>}/>
      

        
      </Routes>
      </BrowserRouter>
     
    </div>
  );
};

export default App;
