// LoginForm.js
import React, { useState } from 'react';
import styles from '../styles/LoginForm.css'

const LoginForm = () => {
  const [isLoginForm, setIsLoginForm] = useState(true);

  const toggleForm = () => {
    setIsLoginForm(!isLoginForm);
  };

  return (
    <div className={styles.container}>
      <input type="checkbox" id="flip" />
      <div className={styles.cover}>
        <div className={styles.front}>
          <img src="img/frontImg.jpg" alt="" />
          <div className={styles.text}>
            <span className={styles.text1}>
              Every new friend is a <br /> new adventure
            </span>
            <span className={styles.text2}>Let's get connected</span>
          </div>
        </div>
        <div className={styles.back}>
          <img className={styles.backImg} src="img/backImg.jpg" alt="" />
          <div className={styles.text}>
            <span className={styles.text1}>
              Complete miles of journey <br /> with one step
            </span>
            <span className={styles.text2}>Let's get started</span>
          </div>
        </div>
      </div>
      <div className={styles.forms}>
        <div className={styles.formContent}>
          {isLoginForm ? (
            <div className={styles.loginForm}>
              <div className={styles.title}>Login</div>
              <form action="" method="post">
                {/* ... */}
              </form>
            </div>
          ) : (
            <div className={styles.signupForm}>
              <div className={styles.title}>Signup</div>
              <form action="" method="post">
                {/* ... */}
              </form>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default LoginForm;
