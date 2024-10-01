# meta-sifive
SiFive BSPs layer for OpenEmbedded/Yocto

## Description

This is the general hardware-specific BSP layer for the SiFive boards.

More information can be found at: <https://sifive.com/> (Official Site)

## Dependencies

This layer depends on:
* https://github.com/openembedded/bitbake
* https://github.com/openembedded/openembedded-core

## Prerequisites for the build host

* [Yocto](https://docs.yoctoproject.org/singleindex.html#compatible-linux-distribution)
* [kas](https://kas.readthedocs.io/en/latest/userguide.html#dependencies-installation)

### Create the build environment

```bash
mkdir dist && cd dist
git clone https://github.com/sifive/meta-sifive -b rel/meta-sifive/hifive-premier-p550
```

## Available machines

This layer provides a BSP for the following machine:
* `hifive-premier-p550`: The SiFive HiFive Premier P550 board

## Available distribution

This layer doesn't define new distributions.

## Running on Hardware

You will find all available build fragments in $BUILDDIR/tmp/deploy/images/hifive-premier-p550
* bootloader_ddr5_secboot.bin - This image is a combination of DDR Firmware, Second Boot Firmware and fw_payload(U-Boot and OpenSBI)
* fitImage - Image comprising of kernel image and DTB
* demo-coreip-xfce4-hifive-premier-p550.rootfs.ext4.xz - rootfs image

### Connecting Using Serial Console

Connect your HiFive Premier P550 board to your PC using USB Type-C cable to access serial console.

For Linux, run: `sudo minicom -wD /dev/ttyUSB2`
Set baudrate to 115200

To quit minicom, hit Ctrl - A followed by 'Z' key. Then Press 'X' key. This will
prompt user to have choice from 'Yes' and 'No'. Select 'Yes' option and press
'Enter' key.

You can login with `root` account. The password is `sifive`. __We strongly
recommend to change the default password  for the root account on the first
boot before you connect it to the Internet.__

### Connecting Using SSH

__Before you connect your board to the Internet we strongly recommend to change
the default password for the root account and configure your network equipment
(for example, routers and firewalls) appropriately.__

SSH daemon is enabled by default, in order to be able to execute remotely
runtime tests. To disable SSH daemon connect to the board using serial console
method described above. Once connected execute the following commands:

```
systemctl disable sshd.socket
systemctl stop sshd.socket
```

The HiFive Premier P550 behave like any other network capable
device (such as PC, laptop, and other Single Board Computers).
Connect the board to your network (for example, a router), and it will acquire
IPv4 + DNS configuration using DHCP protocol. You can use your router
management panel to get assigned IPv4 address or use the serial console to
acquire it directly from the board (use `ip addr` command to print active
network information). Finally you can SSH to the board:

```
ssh -o PreferredAuthentications=password -o PubkeyAuthentication=no -o StrictHostKeyChecking=no -o "UserKnownHostsFile /dev/null" root@<IPv4>
```

### Supported GPUs

Few GPUs from AMD were successfully tested with this board. In particular
Radeon HD 6350, Radeon R7 and RX 550 are tested with no issues.
