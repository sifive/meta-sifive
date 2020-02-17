#!/bin/bash
# Bootstrapper for buildbot slave

DIR="build"
MACHINE="freedom-u540"
CONFFILE="conf/auto.conf"
BITBAKEIMAGE="demo-coreip-cli"

# clean up the output dir
#echo "Cleaning build dir"
#rm -rf $DIR

# make sure sstate is there
#echo "Creating sstate directory"
#mkdir -p ~/sstate/$MACHINE

# fix permissions set by buildbot
#echo "Fixing permissions for buildbot"
#umask -S u=rwx,g=rx,o=rx
#chmod -R 755 .

# Reconfigure dash on debian-like systems
which aptitude > /dev/null 2>&1
ret=$?
if [ "$(readlink /bin/sh)" = "dash" -a "$ret" = "0" ]; then
  sudo aptitude install expect -y
  expect -c 'spawn sudo dpkg-reconfigure -freadline dash; send "n\n"; interact;'
elif [ "${0##*/}" = "dash" ]; then
  echo "dash as default shell is not supported"
  return
fi
# bootstrap OE
echo "Init OE"
export BASH_SOURCE="openembedded-core/oe-init-build-env"
. ./openembedded-core/oe-init-build-env $DIR

if [ -e $CONFFILE ]; then
    echo "Your build directory already has local configuration file!"
    echo "If you want to start from scratch remove old build directory:"
    echo ""
    echo "    rm -rf $PWD"
    echo ""
else

# Symlink the cache
#echo "Setup symlink for sstate"
#ln -s ~/sstate/${MACHINE} sstate-cache

# add the missing layers
echo "Adding layers"
bitbake-layers add-layer ../meta-openembedded/meta-oe
bitbake-layers add-layer ../meta-openembedded/meta-python
bitbake-layers add-layer ../meta-openembedded/meta-multimedia
bitbake-layers add-layer ../meta-openembedded/meta-networking
bitbake-layers add-layer ../meta-openembedded/meta-gnome
bitbake-layers add-layer ../meta-openembedded/meta-xfce
bitbake-layers add-layer ../meta-riscv
bitbake-layers add-layer ../meta-sifive

# fix the configuration
echo "Creating auto.conf"

#if [ -e $CONFFILE ]; then
#    rm -rf $CONFFILE
#fi
cat <<EOF > $CONFFILE
MACHINE ?= "${MACHINE}"
#IMAGE_FEATURES += "tools-debug"
#IMAGE_FEATURES += "tools-tweaks"
#IMAGE_FEATURES += "dbg-pkgs"
# rootfs for debugging
#IMAGE_GEN_DEBUGFS = "1"
#IMAGE_FSTYPES_DEBUGFS = "tar.gz"
#EXTRA_IMAGE_FEATURES_append = " ssh-server-dropbear"
EXTRA_IMAGE_FEATURES_append = " package-management"
PACKAGECONFIG_append_pn-qemu-native = " sdl"
PACKAGECONFIG_append_pn-nativesdk-qemu = " sdl"
USER_CLASSES ?= "buildstats buildhistory buildstats-summary image-mklibs image-prelink"

require conf/distro/include/no-static-libs.inc
require conf/distro/include/yocto-uninative.inc
require conf/distro/include/security_flags.inc

INHERIT += "uninative"

DISTRO_FEATURES_append = " largefile opengl ptest multiarch wayland pam  systemd "
DISTRO_FEATURES_BACKFILL_CONSIDERED += "sysvinit"
VIRTUAL-RUNTIME_init_manager = "systemd"
HOSTTOOLS_NONFATAL_append = " ssh"

# We use NetworkManager instead
PACKAGECONFIG_remove_pn-systemd = "networkd"

# Ignore recipes not yet adjusted for Python 3
# (Bitbake fails to parse them)
BBMASK += "python-networkmanager_2.1.bb \
  ufw_0.33.bb \
  python-ldap_3.2.0.bb \
  mysql-python_1.2.5.bb \
  python-distutils-extra.bb \
  python-futures_3.3.0.bb \
  python-jsmin_2.2.2.bb \
  python-pytoml_0.1.21.bb \
  python-six_1.13.0.bb \
  python-which_1.1.0.bb \
  openlmi-tools_0.10.5.bb \
  gateone_git.bb \
  python-gsocketpool_0.1.6.bb \
  python-h2_3.1.1.bb \
  python-hpack_3.0.0.bb \
  python-hyperframe_5.2.0.bb \
  python-mprpc_0.1.17.bb \
  python-priority_1.3.0.bb \
  python-pyconnman_0.2.0.bb \
  python-pyro4_4.77.bb \
  python-pytun_2.3.0.bb \
  python-thrift_0.13.0.bb \
  python-txws_0.9.1.bb \
  gyp-py2_git.bb \
  python-jsonref.inc \
  pamela_0.3.0.bb \
  pyrtm_0.4.2.bb \
  python-alembic_1.3.2.bb \
  python-anyjson_0.3.3.bb \
  python-appdirs_1.4.3.bb \
  python-asn1crypto_1.3.0.bb \
  python-astroid_2.3.3.bb \
  python-atomicwrites_1.3.0.bb \
  python-attr_0.3.1.bb \
  python-attrs_19.3.0.bb \
  python-automat_0.8.0.bb \
  python-aws-iot-device-sdk-python_1.4.7.bb \
  python-babel_2.7.0.bb \
  python-backports-abc_0.5.bb \
  python-backports-functools-lru-cache_1.5.bb \
  python-backports-ssl_3.7.0.1.bb \
  python-bcrypt_3.1.7.bb \
  python-beautifulsoup4_4.8.2.bb \
  python-behave_1.2.6.bb \
  python-bitarray_1.2.1.bb \
  python-blinker_1.4.bb \
  python-cachetools_4.0.0.bb \
  python-can_3.3.2.bb \
  python-certifi_2019.11.28.bb \
  python-cffi_1.13.2.bb \
  python-chardet_3.0.4.bb \
  python-cheetah_2.4.4.bb \
  python-click_7.0.bb \
  python-cmd2_0.9.23.bb \
  python-coloredlogs_10.0.bb \
  python-configargparse_0.15.1.bb \
  python-configparser_3.8.1.bb \
  python-constantly_15.1.0.bb \
  python-contextlib2_0.6.0.bb \
  python-crcmod_1.7.bb \
  python-cryptography-vectors_2.8.bb \
  python-cryptography_2.8.bb \
  python-cython_0.29.14.bb \
  python-daemon_2.1.2.bb \
  python-daemonize_2.5.0.bb \
  python-dateutil_2.8.1.bb \
  python-dbusmock_0.16.7.bb \
  python-decorator_4.4.1.bb \
  python-django-south_1.0.2.bb \
  python-django_1.11.14.bb \
  python-djangorestframework_3.9.0.bb \
  python-dnspython_1.16.0.bb \
  python-docutils_0.15.2.bb \
  python-dominate_2.4.0.bb \
  python-editor_1.0.4.bb \
  python-engineio_3.11.2.bb \
  python-enum34_1.1.6.bb \
  python-evdev_1.2.0.bb \
  python-feedformatter_0.4.bb \
  python-feedparser_5.2.1.bb \
  python-flask-babel_0.12.2.bb \
  python-flask-bcrypt_0.7.1.bb \
  python-flask-bootstrap_3.3.7.1.bb \
  python-flask-login_0.4.1.bb \
  python-flask-mail_0.9.1.bb \
  python-flask-migrate_2.5.2.bb \
  python-flask-nav_0.6.bb \
  python-flask-navigation_0.2.0.bb \
  python-flask-pymongo_2.3.0.bb \
  python-flask-restful_0.3.7.bb \
  python-flask-script_2.0.6.bb \
  python-flask-sijax_0.4.1.bb \
  python-flask-socketio_4.2.1.bb \
  python-flask-sqlalchemy_2.4.1.bb \
  python-flask-uploads_0.2.1.bb \
  python-flask-user_0.6.19.bb \
  python-flask-wtf_0.14.2.bb \
  python-flask-xstatic_0.0.1.bb \
  python-flask_1.1.1.bb \
  python-funcsigs_1.0.2.bb \
  python-functools32_3.2.3-2.bb \
  python-future_0.18.2.bb \
  python-gevent_1.4.0.bb \
  python-grpcio-tools_1.14.1.bb \
  python-grpcio_1.19.0.bb \
  python-html5lib_1.0.1.bb \
  python-humanfriendly_4.18.bb \
  python-humanize_0.5.1.bb \
  python-hyperlink_19.0.0.bb \
  python-idna_2.8.bb \
  python-importlib-metadata_0.23.bb \
  python-incremental_17.5.0.bb \
  python-inflection_0.3.1.bb \
  python-intervals_1.10.0.bb \
  python-ipaddress_1.0.23.bb \
  python-ipy_1.00.bb \
  python-iso8601_0.1.12.bb \
  python-isodate_0.6.0.bb \
  python-isort_4.3.21.bb \
  python-itsdangerous_1.1.0.bb \
  python-javaobj-py3_0.4.0.1.bb \
  python-jinja2_2.10.3.bb \
  python-jsonpatch_1.24.bb \
  python-jsonpointer_2.0.bb \
  python-jsonschema_3.2.0.bb \
  python-lazy-object-proxy_1.4.3.bb \
  python-linecache2_1.0.0.bb \
  python-lockfile_0.12.2.bb \
  python-lrparsing_1.0.16.bb \
  python-lxml_4.4.2.bb \
  python-m2crypto_0.30.1.bb \
  python-mako_1.1.0.bb \
  python-markupsafe_1.1.1.bb \
  python-mccabe_0.4.0.bb \
  python-mock_3.0.5.bb \
  python-monotonic_1.5.bb \
  python-more-itertools_5.0.0.bb \
  python-msgpack_0.6.2.bb \
  python-ndg-httpsclient_0.5.1.bb \
  python-netaddr_0.7.19.bb \
  python-netifaces_0.10.9.bb \
  python-networkx_2.2.bb \
  python-packaging_20.0.bb \
  python-paho-mqtt_1.5.0.bb \
  python-pam_1.8.2.bb \
  python-parse-type_0.4.2.bb \
  python-parse_1.14.0.bb \
  python-passlib_1.7.2.bb \
  python-paste_3.2.3.bb \
  python-pathlib2_2.3.5.bb \
  python-pbr_5.4.4.bb \
  python-pep8_1.7.1.bb \
  python-periphery_2.0.1.bb \
  python-pexpect_4.7.0.bb \
  python-pika_1.1.0.bb \
  python-pint_0.9.bb \
  python-pip_19.3.1.bb \
  python-pluggy_0.13.1.bb \
  python-ply_3.11.bb \
  python-pretend_1.0.9.bb \
  python-prettytable_0.7.2.bb \
  python-progress_1.5.bb \
  python-prompt-toolkit_2.0.10.bb \
  python-protobuf_3.9.2.bb \
  python-psutil_5.6.7.bb \
  python-ptyprocess_0.6.0.bb \
  python-py_1.8.1.bb \
  python-pyalsaaudio_0.8.4.bb \
  python-pyasn1-modules_0.2.7.bb \
  python-pyasn1_0.4.8.bb \
  python-pybind11_2.4.3.bb \
  python-pybluez_0.22.bb \
  python-pycodestyle_2.5.0.bb \
  python-pycparser_2.19.bb \
  python-pycryptodome_3.9.4.bb \
  python-pycurl_7.43.0.3.bb \
  python-pydbus_0.6.0.bb \
  python-pyelftools_0.25.bb \
  python-pyexpect_1.0.19.bb \
  python-pyfirmata_1.1.0.bb \
  python-pyflakes_2.1.1.bb \
  python-pygpgme_0.3.bb \
  python-pyhamcrest_1.9.0.bb \
  python-pyiface_0.0.11.bb \
  python-pyinotify_0.9.6.bb \
  python-pyjks_19.0.0.bb \
  python-pyjwt_1.7.1.bb \
  python-pylint_1.8.3.bb \
  python-pymisp_2.4.119.1.bb \
  python-pymongo_3.10.0.bb \
  python-pymysql_0.9.3.bb \
  python-pynetlinux_1.1.bb \
  python-pyopenssl_19.1.0.bb \
  python-pyparsing_2.4.6.bb \
  python-pyperclip_1.7.0.bb \
  python-pyperf_1.7.0.bb \
  python-pyroute2_0.5.7.bb \
  python-pyrsistent_0.15.7.bb \
  python-pyserial_3.4.bb \
  python-pysmi_0.3.4.bb \
  python-pysnmp_4.4.9.bb \
  python-pysocks_1.7.1.bb \
  python-pysqlite_2.8.3.bb \
  python-pystache_0.5.4.bb \
  python-pytest-helpers-namespace_2019.1.8.bb \
  python-pytest-runner_5.2.bb \
  python-pytest-tempdir_2019.10.12.bb \
  python-pytest_5.3.2.bb \
  python-pytz_2019.3.bb \
  python-pyudev_0.21.0.bb \
  python-pyusb_1.0.2.bb \
  python-pyyaml_5.2.bb \
  python-pyzmq_17.1.0.bb \
  python-rdflib_4.2.2.bb \
  python-redis_2.10.6.bb \
  python-requests-oauthlib_1.3.0.bb \
  python-requests_2.22.0.bb \
  python-rfc3339-validator_0.1.1.bb \
  python-rfc3986-validator_0.1.1.bb \
  python-rfc3987_1.3.8.bb \
  python-robotframework-seriallibrary_0.3.1.bb \
  python-robotframework_3.0.4.bb \
  python-scandir_1.10.0.bb \
  python-scrypt_0.8.6.bb \
  python-sdnotify_0.3.2.bb \
  python-selectors34_1.2.bb \
  python-semver_2.8.1.bb \
  python-serpent_1.28.bb \
  python-setuptools-scm_3.3.3.bb \
  python-sh_1.12.14.bb \
  python-sijax_0.3.2.bb \
  python-simplejson_3.17.0.bb \
  python-singledispatch_3.4.0.3.bb \
  python-slip-dbus_0.6.5.bb \
  python-snakefood_1.4.bb \
  python-snimpy_0.8.13.bb \
  python-socketio_4.3.1.bb \
  python-soupsieve_1.9.4.bb \
  python-sparts_0.7.3.bb \
  python-speaklater_1.3.bb \
  python-sqlalchemy_1.3.12.bb \
  python-sqlparse_0.3.0.bb \
  python-statistics_1.0.3.5.bb \
  python-stevedore_1.31.0.bb \
  python-strict-rfc3339_0.7.bb \
  python-subprocess32_3.2.7.bb \
  python-systemd_234.bb \
  python-toml_0.10.0.bb \
  python-tornado-redis_2.4.18.bb \
  python-tornado_6.0.3.bb \
  python-tqdm_4.41.1.bb \
  python-traceback2_1.4.0.bb \
  python-twisted_19.10.0.bb \
  python-twitter_3.8.0.bb \
  python-twofish_0.3.0.bb \
  python-typing_3.7.4.1.bb \
  python-tzlocal_2.0.0.bb \
  python-ujson_1.35.bb \
  python-unidiff_0.5.5.bb \
  python-urllib3_1.25.7.bb \
  python-vcversioner_2.16.0.0.bb \
  python-versiontools_1.9.1.bb \
  python-visitor_0.1.3.bb \
  python-vobject_0.9.6.1.bb \
  python-waitress_1.4.2.bb \
  python-wcwidth_0.1.8.bb \
  python-webcolors_1.8.1.bb \
  python-webencodings_0.5.1.bb \
  python-werkzeug_0.16.0.bb \
  python-whoosh_2.7.4.bb \
  python-wrapt_1.11.2.bb \
  python-wtforms_2.2.1.bb \
  python-xlrd_1.2.0.bb \
  python-xstatic-font-awesome_4.7.0.0.bb \
  python-xstatic_1.0.2.bb \
  python-yappi.inc \
  python-yappi_1.0.bb \
  python-zipp_0.6.0.bb \
  python-zopeinterface_4.7.1.bb \
  python3-robotframework-seriallibrary_0.3.1.bb \
  python-cson_git.bb \
  python-pyephem_3.7.7.0.bb \
  python-pywbem_0.15.0.bb \
  python-pyparted_3.11.3.bb \
  python-yappi_1.0.bb \
  python3-yappi_1.0.bb \
  python-pyrex_0.9.9.bb \
  python-webdav_0.1.2.bb \
  gammu_1.32.0.bb \
  dnf-plugin-tui_git.bb \
  guider_3.9.6.bb \
  python-cpuset_1.6.bb \
  python-pygobject_3.34.0.bb \
  iotop_0.6.bb \
  libpwquality_1.4.0.bb \
  mraa_git.bb \
  sanlock_3.8.0.bb \
  upm_git.bb \
  pyxdg_0.26.bb \
  dnfdragora_git.bb \
  libiio_git.bb \
  lio-utils_4.1.bb \
  opencv_4.1.0.bb \
  opencv_4.1.0.bb \
  cxxtest_4.4.bb \
  python-dbus_1.2.14.bb \
  python-epydoc_3.0.1.bb \
  python-gdata_2.0.18.bb \
  python-greenlet_0.4.15.bb \
  python-imaging_1.1.7.bb \
  python-numeric_24.2.bb \
  python-pycrypto_2.6.1.bb \
  python-pyrex_0.9.9.bb \
  python-smbus_4.1.bb \
  python-webdav_0.1.2.bb \
  python3-dbussy_1.2.1.bb \
  python3-greenlet_0.4.15.bb \
  python3-pycrypto_2.6.1.bb \
  python3-smbus_4.1.bb \
  python-pyparted_3.11.3.bb \
  python3-pyparted_3.11.3.bb \
  catfish_1.4.11.bb \
  menulibre_2.2.1.bb \
  crda_3.18.bb \
  fetchmail_6.4.1.bb \
  nghttp2_1.40.0.bb \
  ntop_5.0.1.bb \
  openipmi_2.0.27.bb \
  spice_git.bb \
  telepathy-idle_0.2.0.bb \
  postgresql.inc \
  nanopb_0.4.0.bb \
  nodejs_10.17.0.bb \
  php_7.3.11.bb \
  sip_4.19.19.bb \
  yasm_git.bb \
  collectd_5.8.1.bb \
  hplip_3.12.6.bb \
  libplist_2.1.0.bb \
  mozjs_60.5.2.bb \
  rrdtool_1.7.2.bb \
  fvwm_2.6.9.bb \
  ttf-lohit_2.bb \
  mpv_0.26.0.bb \
  gpsd_3.19.bb \
  nmap_7.80.bb \
  telepathy-python_0.15.19.bb \
  python-pyrex-native_0.9.9.bb \
  cherokee_git.bb \
  python-jsonref_0.2.bb \
  crda_3.18.bb \
  freeradius_3.0.19.bb \
  fetchmail_6.4.1.bb \
  lowpan-tools_git.bb \
  spice_git.bb \
  python-which_1.1.0.bb \
  sip_4.19.19.bb \
  hplip_3.12.6.bb \
  rrdtool_1.7.2.bb \
  gpsd_3.19.bb \
  system-config-keyboard_1.4.0.bb \
  gateone_git.bb \
  python-pylint_1.8.3.bb \
  python-scrypt_0.8.6.bb \
  postgresql_12.1.bb \
  "

# Disable security flags for bootloaders
# Security flags incl. smatch protector which is not supported in these packages
SECURITY_CFLAGS_pn-freedom-u540-c000-bootloader = ""
SECURITY_LDFLAGS_pn-freedom-u540-c000-bootloader = ""

SECURITY_CFLAGS_pn-opensbi = ""
SECURITY_LDFLAGS_pn-opensbi = ""

# Add r600 drivers for AMD GPU
PACKAGECONFIG_append_pn-mesa = " r600"

EOF
fi

echo "---------------------------------------------------"
echo "MACHINE=${MACHINE} bitbake ${BITBAKEIMAGE}"
echo "---------------------------------------------------"
echo ""
echo "Buildable machine info"
echo "---------------------------------------------------"
echo "* freedom-u540: The SiFive HiFive Unleased board"
echo "* qemuriscv64: The 64-bit RISC-V machine"
echo "---------------------------------------------------"

# start build
#echo "Starting build"
#bitbake $BITBAKEIMAGE

