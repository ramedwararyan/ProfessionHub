import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFacebookF, faTwitter, faYoutube, faLinkedinIn } from '@fortawesome/free-brands-svg-icons';
import '../styles/LandingPage.css'

const LandingPage = () => {
  return (
    <div>
      <section className="hero">
        <header>
          <div className="container">
            <div className="logo">
              <h2>
                Profession Hub<span></span>
              </h2>
            </div>

            <nav className="menu-overlay">
              <ul>
                <li>
                  <a href="#">About</a>
                </li>
                <li>
                  <a href="#">Services</a>
                </li>
                <li>
                  <a href="#">Contact</a>
                </li>
                <li className="action">
                  <a href="#">Learn More</a>
                </li>
              </ul>
            </nav>
          </div>
        </header>

        <div className="details">
          <div className="container">
            <h1>
              where career <br />
              finds you
            </h1>
            <p>
              Choosing a career or finding a roadmap has always been a mess. Well !! PROFESSION HUB is here to help
              you.
            </p>
            <a href="/login">Sign Up</a>
            <a href="/login">Log In</a>
          </div>
        </div>

        <div className="connect">
          <div className="container">
            <h3>Connect with us</h3>
            <div className="links">
              <a href="#">
                <FontAwesomeIcon icon={faFacebookF} />
              </a>
              <a href="#">
                <FontAwesomeIcon icon={faTwitter} />
              </a>
              <a href="#">
                <FontAwesomeIcon icon={faYoutube} />
              </a>
              <a href="#">
                <FontAwesomeIcon icon={faLinkedinIn} />
              </a>
            </div>
          </div>
        </div>
      </section>

      <div className="hamb">
        <span></span>
        <span></span>
        <span></span>
      </div>

      {/* Add your script component or import it */}
      {/* <script src="/js/script.js"></script> */}
    </div>
  );
};

export default LandingPage;
