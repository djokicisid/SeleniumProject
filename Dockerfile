# Use the Maven image as the base image
FROM maven:3.8.4-jdk-11

# Install Xvfb
RUN apt-get update && apt-get install -y xvfb

# Install necessary dependencies
RUN apt-get update && \
    apt-get install -y libnss3-dev

# Install necessary dependencies
RUN apt-get update && \
    apt-get install -y wget gnupg && \
    wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list' && \
    apt-get update && \
    apt-get install -y google-chrome-stable

# Update package lists and install required tools (wget and unzip)
RUN apt-get update && apt-get install -y wget unzip

# Install ChromeDriver
RUN wget https://storage.googleapis.com/chrome-for-testing-public/122.0.6261.69/linux64/chromedriver-linux64.zip -P /tmp/ \
    && unzip /tmp/chromedriver-linux64.zip -d /tmp/ \
    && mv /tmp/chromedriver-linux64/chromedriver /usr/local/bin/ \
    && rm -rf /tmp/chromedriver-linux64 /tmp/chromedriver-linux64.zip \
    && chmod +x /usr/local/bin/chromedriver

# Set Chrome as the default browser
ENV BROWSER=/usr/bin/google-chrome-stable

# Set the display to use Chrome in headless mode
ENV DISPLAY=:99

# Start Xvfb when the container starts
CMD ["Xvfb", "-ac", ":99", "-screen", "0", "1280x1024x16"]

# Install Firefox
ARG FIREFOX_VERSION=103.0.2
RUN echo "deb http://deb.debian.org/debian/ unstable main contrib non-free" >> /etc/apt/sources.list.d/debian.list
RUN apt-get update -qqy \
&& apt-get -qqy --no-install-recommends install firefox \
&& rm -rf /var/lib/apt/lists/* /var/cache/apt/* \
&& wget --no-verbose -O /tmp/firefox.tar.bz2 https://download-installer.cdn.mozilla.net/pub/firefox/releases/$FIREFOX_VERSION/linux-x86_64/en-US/firefox-$FIREFOX_VERSION.tar.bz2 \
&& apt-get -y purge firefox \
&& rm -rf /opt/firefox \
&& tar -C /opt -xjf /tmp/firefox.tar.bz2 \
&& rm /tmp/firefox.tar.bz2 \
&& mv /opt/firefox /opt/firefox-$FIREFOX_VERSION \
&& ln -fs /opt/firefox-$FIREFOX_VERSION/firefox /usr/bin/firefox
# Install GeckoDriver
ARG GECKODRIVER_VERSION=0.31.0
RUN wget --no-verbose -O /tmp/geckodriver.tar.gz https://github.com/mozilla/geckodriver/releases/download/v$GECKODRIVER_VERSION/geckodriver-v$GECKODRIVER_VERSION-linux64.tar.gz \
&& rm -rf /opt/geckodriver \
&& tar -C /opt -zxf /tmp/geckodriver.tar.gz \
&& rm /tmp/geckodriver.tar.gz \
&& mv /opt/geckodriver /opt/geckodriver-$GECKODRIVER_VERSION \
&& chmod 755 /opt/geckodriver-$GECKODRIVER_VERSION \
&& ln -fs /opt/geckodriver-$GECKODRIVER_VERSION /usr/bin/geckodriver