/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.rng.core.source32;

import org.apache.commons.rng.core.RandomAssert;
import org.junit.Test;

public class MersenneTwisterTest {
    @Test
    public void testMakotoNishimura() {
        final MersenneTwister rng = new MersenneTwister(new int[] { 0x123, 0x234, 0x345, 0x456 });

        /*
         * Data from
         *   http://www.math.sci.hiroshima-u.ac.jp/~m-mat/MT/MT2002/CODES/mt19937ar.out
         * converted to hexadecimal.
         */
        final int[] expectedSequence = {
            0x3fa23623, 0x38fa935f, 0x1c72dc38, 0xf4cf2f5f, 0xfc110f5c, 0xc75677aa, 0xc802152f, 0x0d9155da,
            0x304aacd1, 0x9a73f337, 0x989a7a43, 0xc1483a50, 0x268c922d, 0x582fa6ba, 0xfd0cc411, 0x44267b5e,
            0xe64aeede, 0xbffce512, 0x69b7263d, 0x43df2416, 0x54c06fe4, 0x4bb1636f, 0xaa772159, 0x692b9302,
            0xe6f6290f, 0xec59faf1, 0x0453050b, 0x8e18c8c2, 0x9afc3045, 0xc0f8369c, 0xa6784b64, 0x2b3baca5,
            0x241c69b2, 0x102b153e, 0x2aa0204a, 0xc4937ab0, 0x4edada28, 0xfc1a4165, 0x5327669e, 0xdeaa3938,
            0x7d45d251, 0xde57ac80, 0xafdc0e1a, 0x630eea2f, 0xac9709be, 0x8d4b2277, 0x3bd93aca, 0x85eff969,
            0x7dd7008b, 0xc0f5f7ef, 0xf354dbb7, 0x0d19a749, 0x9245ad51, 0xdd1bbe7b, 0x31ce56ca, 0x8fc36f3b,
            0xa307eaa6, 0x2d16123c, 0x81a710e8, 0x6fb0ece0, 0xa4a8ed5d, 0xf81025ee, 0xc7bdb7cc, 0x0acbd297,
            0xc2e69738, 0x2e414667, 0x8436773b, 0xb2eb0184, 0x6f458090, 0x7f2c9350, 0x0213f68e, 0x470b7a6d,
            0xb8e983ba, 0xadd68783, 0x3c580a4a, 0x8239e155, 0xfdc2c299, 0xacd2d0b2, 0xe39381d6, 0xb4a5ad7e,
            0x4c6c3bdd, 0x1908bf3a, 0x8aaa5fe5, 0xa3674b65, 0x4c2d0c3f, 0xdf2ba5a5, 0x1032fcf8, 0x9c8a9da2,
            0x989f1b17, 0xb62684e4, 0xfc56810e, 0x4937dc5d, 0xd6502fba, 0x403fad7e, 0x8ecf04fa, 0x6e622af6,
            0xb3a12932, 0x7735265b, 0xb3397c02, 0x3e92651e, 0x58bca8af, 0xd02046e6, 0x06394b11, 0x91ed9821,
            0xb75225a3, 0xe6cf1b38, 0x35297ffe, 0xeaa2f3af, 0x8740f423, 0x9cf755ec, 0x3e71ab47, 0x9b3f3b19,
            0xa17cb981, 0x745c768b, 0x0c5fa06c, 0xa9ddfe32, 0x27fb2a2d, 0x83c11cc4, 0x1be0b4bd, 0x0fadc6d9,
            0xd4c4cf4b, 0x3e2019a7, 0xe6489797, 0x5746fcb5, 0xa468a4c8, 0xe1f303c8, 0x892aba04, 0xb92617d6,
            0x0079af91, 0xa6719544, 0x2123c124, 0x250f6867, 0x4ed30865, 0x32e1592c, 0x28f2364b, 0x56bb6094,
            0x39162749, 0x6b68d894, 0x3ce35fee, 0xcfc8dc4f, 0x71602f12, 0xdc9ae288, 0xf8ef2ed7, 0x6b06d07a,
            0x216e06f0, 0xdec994b2, 0xbb3a7736, 0x5c9957e9, 0xc8bca92a, 0x2a6955f6, 0x93876aff, 0x0fac9a03,
            0x0efc4f05, 0xb1a05dc2, 0x6bae0207, 0x39c9d223, 0xd25d4245, 0xa3194800, 0xb20d013e, 0x6249fe2f,
            0x837f9243, 0xb5af74d1, 0xda4d5e81, 0xbb17131c, 0x9e8e92bc, 0x631fa28c, 0x6c4862df, 0x188d56e3,
            0xe7b5f3c2, 0x8be50cef, 0x3c846d8b, 0x47bd5cf0, 0x816608b6, 0x99263fac, 0x3082b3dd, 0x41e2e09e,
            0x554d804b, 0x0d25a3b7, 0xfcf0b24b, 0xf91f82af, 0x9b6ad24b, 0xb83703f9, 0x10d431ab, 0x675e6dc2,
            0x52f58561, 0x3badef9a, 0x31592d6b, 0x70748fc3, 0xea8cd203, 0x9cdde8aa, 0xe8c41002, 0x0696ec53,
            0x91e8d6be, 0xdd90041f, 0xc4fb23f3, 0xdee5fd0f, 0xb65694ac, 0x38dba6c3, 0x9a6226c2, 0x4380bf2d,
            0xe23c29cf, 0xa62df7dd, 0x3a8a7f47, 0xcef7d974, 0x3341ae98, 0x9fcced1f, 0x89117a75, 0x2697345b,
            0x0dec75a6, 0x243febfb, 0xdbe6ab5d, 0x6e41602c, 0x5fded1ce, 0xec854a95, 0xa0839470, 0xa9fc1f3c,
            0x0eb51cb9, 0xd58d7944, 0xc6767655, 0xf096123e, 0x3c60b5b2, 0x38abc388, 0xec0546a8, 0x974f331d,
            0x0b68e0fe, 0x9e7008a7, 0xc3496c73, 0xae81ba8c, 0x499065db, 0x727d51f3, 0xd2c437c1, 0x5b879655,
            0x822f5cf7, 0xc3525dae, 0x198a3598, 0x8dc3afd0, 0xf1fb2ace, 0xe9470165, 0xa2d922ee, 0x03d634c3,
            0xdfdafa3a, 0x323cb60d, 0xa8142cc2, 0x8fedaffd, 0x3c344d7e, 0x6da7d412, 0x8faa6de0, 0x091b26b9,
            0xcfb90648, 0xf4811a08, 0xaa091e50, 0x3472e158, 0x6a650e2e, 0xa3cf7e2f, 0x50c01ec0, 0xc2c67020,
            0x87402425, 0xb2e34cb9, 0xbd87d91b, 0x3563a6d3, 0x339cf74e, 0xffcc2cf9, 0x05537400, 0x57ae171b,
            0xf5396a89, 0xbed9b927, 0xaaea3a07, 0x92add60c, 0xd6cec30b, 0x85729ab0, 0xc5326ca8, 0x7b83f953,
            0xa43ff0cf, 0xe0eea920, 0x65de4954, 0xff454bcb, 0xa3af3b3a, 0xa8d5900a, 0x19d5c121, 0xbd4a89ac,
            0x019084ae, 0x98130311, 0x0aeba10a, 0xe80fa57c, 0x72ed83fd, 0x4fb367f3, 0xcc63dc6a, 0xc3372454,
            0x6a37e254, 0xfe2da32c, 0x6fda8ee3, 0xffaf02b4, 0xc027d10e, 0x6709f3e9, 0xf27c7cfe, 0xaaefc51f,
            0x446a04a8, 0x120d944c, 0x6723ed43, 0x72ab3b17, 0x465e7e94, 0x5d910b0f, 0x0bd96427, 0x216d0655,
            0x7d9b8ad4, 0xa14a22ac, 0xcd90160a, 0xdb3f890b, 0x2d37dcc3, 0x34b8c67f, 0x1cfd3624, 0x69ca4cc3,
            0xe756cff2, 0x9d122b27, 0x46667c33, 0x59ee9c5c, 0xbd7b82d1, 0x5f0576e1, 0x499ef4c9, 0x1370277c,
            0x8954bac1, 0x37da5236, 0xa02eb689, 0xbe47fedc, 0x776ea717, 0x6cb476ac, 0xa47b4a6a, 0x00999efe,
            0x5b639436, 0xf650de88, 0x04e8fd98, 0x191216d2, 0xceaed09b, 0x204794eb, 0xd2c25028, 0x87bd221d,
            0xc68852c5, 0xbfaafd1e, 0xf534182b, 0xfaa3297f, 0x80e14a8d, 0xc204c912, 0x84449c0d, 0xb184ee7d,
            0x0f3f7b53, 0xaa6e3294, 0xb89cbf37, 0x992ad523, 0x6efb1e90, 0xad46858f, 0xa217c69e, 0x3b8123f6,
            0x9651ad17, 0x8f4c0a9a, 0x0f76fc20, 0x7c62f8c3, 0x6da9dc30, 0x70dd3d5d, 0x96ae2e55, 0x58c655e4,
            0xaa853035, 0x048e81ea, 0x87002693, 0x2c667e97, 0x9a2f4d5b, 0x52933a95, 0x03f72a82, 0x6c6d501a,
            0x713505ec, 0x7d0f9784, 0x6aebb0b6, 0x107a7e37, 0x15773151, 0xf90a74da, 0x2377363c, 0x0a8de6de,
            0xa6e5d5a2, 0x5dca95f2, 0x5c53d917, 0x168ec0cf, 0x570dfc18, 0x4288fe6b, 0xb9833a27, 0xdd1e7904,
            0x315a4474, 0xd5631e5f, 0x7213cfd6, 0x249938a9, 0xff052817, 0x718eb33c, 0x02b4dec3, 0xc4964842,
            0x469f5baa, 0xd941297a, 0xe9dded03, 0x60fac145, 0xdb37f217, 0x39cd8ee4, 0x452c33d1, 0x5abe9be8,
            0x1af69e0d, 0x5b0bd6d7, 0x319ecd5f, 0x3bcd235c, 0x3fdbfa77, 0xac550eb4, 0x3a0a346c, 0xf39919d4,
            0x6e1f71ee, 0xe832ed0e, 0x84c9d733, 0x60a2537d, 0xabbdd4b3, 0xd598dffd, 0xd13c8592, 0x32a03cc7,
            0x336e5faf, 0x2f8e590a, 0xaaeec5d4, 0xa8295b34, 0xc30ce8ca, 0xee4c5501, 0x0c77d054, 0x6b982f24,
            0x00678195, 0xa849138f, 0x2f3dd18e, 0xfe23a88a, 0x2e47871d, 0xe263d69f, 0xaa1419fe, 0xa2b0fa92,
            0x3fe37d3d, 0x4f37161e, 0x4cd9b682, 0xc65860f6, 0x77e4edc3, 0x04c71a18, 0x36fb25b8, 0x451411f4,
            0x635fecf1, 0x92b03a64, 0x9b7fd167, 0x171906d3, 0xc76604e6, 0x59257d37, 0xf444dead, 0xbc26a68d,
            0xd225e427, 0xf675b085, 0x1aa04db0, 0x7f683b77, 0xd79d3278, 0x308425a0, 0x4504d28a, 0xde9ae465,
            0x64275e5a, 0xbd910789, 0xd13421f7, 0x84ce54b4, 0x3c166b93, 0x1d040e83, 0x337c6ae7, 0xbe1630aa,
            0x3e9a6e14, 0xe125a856, 0xffce8ca5, 0x324f5b19, 0x5050a29f, 0xa878afad, 0x2c6ee4b4, 0x7a891b36,
            0xfdeda343, 0x8e420be9, 0xb1a90f55, 0x1aa82dfc, 0x7bd87288, 0x497d36dd, 0xefca266c, 0x536338b5,
            0xbb314af9, 0x99c64a66, 0xe230edff, 0x35b07a32, 0xac172bc3, 0x66890dcd, 0xc8b7e513, 0x9f14818d,
            0x38f45e55, 0xe39d2420, 0x41e7b802, 0xe7d097d7, 0x87bde5db, 0xa3b40719, 0x6903a4f1, 0x8fe17270,
            0xa00bc9ad, 0xfcbd3397, 0x458ad6f3, 0xfb3f1663, 0xb7b4fe23, 0xec0fda7a, 0x6324016b, 0x7c6c5059,
            0xf81c1522, 0x957286ba, 0x5e27c862, 0x2dbb10a2, 0x39db5731, 0x1d567daa, 0x55ee48f2, 0x4e5e0742,
            0xc27142ca, 0xcbacae9e, 0x5d1a105a, 0xb37e6bbc, 0x4457de32, 0xc2731190, 0x51f2e26b, 0x616f5ec8,
            0x4c524088, 0x84eb772e, 0x18fe5f9c, 0xc27be658, 0x025f0b8e, 0x61d91e60, 0x65599112, 0x839a9784,
            0x9942f04b, 0x907c8596, 0x2e824b62, 0xa1d696d8, 0xca1de87f, 0x09b97e72, 0x89a8b34e, 0x6edda0f8,
            0x21202673, 0x10b55868, 0x5fa7c76b, 0xa1b56faf, 0x670e4131, 0xd8f5502e, 0x25233991, 0xe43445a3,
            0xfed6a20a, 0xd19733f7, 0x8bb5db5b, 0x90e132fc, 0x25e17e90, 0xe697ac65, 0x302fda43, 0xb7064f65,
            0xff3caaf3, 0x7cc369c7, 0x789d0230, 0x5d7fe246, 0xcbfd430c, 0xf66fcdee, 0xb3d37457, 0xc24547aa,
            0xac23da09, 0xbddb1df4, 0xfdd7d1fb, 0x4b8987b3, 0x3cf260e1, 0x30a24d85, 0x375fb86c, 0xb287e74a,
            0xc8f1b360, 0xd70e2779, 0x784fa37b, 0x07477485, 0xa787685e, 0x541fbaf2, 0x49d05a21, 0x46bcecac,
            0xbc1c4443, 0x85b0917e, 0x693c3ac2, 0x10a30d08, 0xff192733, 0x61d88012, 0xe2474eaf, 0x00cbb899,
            0x062b8b3a, 0x5ff1fcdb, 0x20dde01f, 0x94a3ef59, 0x4e75b597, 0xea677d68, 0xf9b9c06b, 0x1b9ee46c,
            0xaf1a479a, 0x6e9a7611, 0x9c9284f6, 0x0348a296, 0xe3e3b5ab, 0x2ce8b80a, 0xf11a7efc, 0x4bf1a59c,
            0x4301a8f0, 0x057c6a80, 0x7ebd7550, 0x1963d609, 0x17064918, 0x9a5e486f, 0x767ada6e, 0xf379835d,
            0x817d0eed, 0xc8a9fc9f, 0xd6c0e87d, 0x8dc9a94f, 0xacf56951, 0xdd8bd5ee, 0xdd248898, 0x9e286bc3,
            0xced01226, 0xb88ffe3c, 0xea662cad, 0x9ab07c59, 0x13032ac1, 0xbb873d74, 0x32e0776e, 0x089b5f90,
            0xe09d5b09, 0x2ea60da1, 0x80cfe80b, 0x21fa1ce2, 0x706dc2f1, 0x00d96e10, 0x84f75f04, 0x5fa97783,
            0xea2b6877, 0xe9c417ed, 0x807f00ac, 0x0802442e, 0x19a90570, 0x72ae16ca, 0x107e4fa1, 0x0bb6135e,
            0xd775a370, 0x5ff6f941, 0x6a707d8a, 0x32be82f8, 0xe02e92e3, 0xadf9e5ba, 0xc88902f1, 0xb38bd032,
            0xdeae3543, 0x9d8b53e4, 0xe138fc67, 0x4565a728, 0x0c45115d, 0xab6dfd67, 0xd0d19d8a, 0xb77c9c49,
            0xcef31821, 0x683e0485, 0xa35e3a23, 0x12d6a276, 0x62f81a9c, 0x0a10fe92, 0x38fb7c97, 0xed7c6de5,
            0x21c46edb, 0x3babb813, 0x488587af, 0xaff84a55, 0xe48cce39, 0xe5098cad, 0x9310cf58, 0xece52930,
            0xc21bcc91, 0x540108c4, 0x4f44bcdb, 0x898a9365, 0xba470a57, 0xd7f15ff8, 0xe473ab14, 0xe76833ff,
            0x89997b1b, 0x9e7f7c54, 0x5673fcd9, 0xd289f943, 0xbdeb72e2, 0x490d3961, 0x4302a415, 0x0a7aa0c1,
            0xb35bde2f, 0xa6b2690f, 0xd9acf25b, 0x09950b7e, 0x71621b83, 0x653f519c, 0x43a66e3d, 0x1da1d26a,
            0xc4db0a37, 0xf1d8513e, 0xd6c9840b, 0xf39e866b, 0x197fe72c, 0x11196229, 0x311007b0, 0x61028494,
            0xdf40c6a7, 0x9a54746c, 0xfa75ce2e, 0x03bf7309, 0xc33d7966, 0xeda6af60, 0xa6672387, 0x72411b87,
            0x9a211f11, 0x51f56dc7, 0x53a684e0, 0x0397541a, 0xe33486f2, 0xb4186699, 0x9bbf1cb2, 0x1f86e0f1,
            0xa459fea9, 0xc729c354, 0x9c466f44, 0x10479afe, 0xef53e2d2, 0xb8769c4a, 0x60a7fa28, 0x8b551da9,
            0x6b17bf70, 0x4b0a4a73, 0xfcd67534, 0x7f77d788, 0xb422f68f, 0xf8ccf23c, 0x1ad3e613, 0xc33054e4,
            0x24c8f64a, 0xa135d8b9, 0x5799da51, 0x4ff771cb, 0x15430a3f, 0x46db7ec8, 0x87d4c88f, 0x694bb4c8,
            0xa6a5ff3b, 0x037255c0, 0xac77403c, 0x49a7a6d5, 0x61326bcb, 0xea1febca, 0x19905ce4, 0x6c208616,
            0x88601b2b, 0xf5ecddc0, 0xec8475d1, 0xd3853b52, 0x3d21610b, 0x53b3a29c, 0x77575565, 0x05825b57,
            0x89ebfb88, 0x8f3c2f00, 0xb32ead1a, 0xd44f8744, 0x63a692b2, 0x69697bae, 0x6b7fe1c9, 0x325b5bf1,
            0xb32e0e11, 0x54187523, 0x4501dc49, 0x7eb3aed1, 0x6ab0eb88, 0x6b3e94bf, 0x2c2d293b, 0x8f668ba5,
            0x2db81bb2, 0x9e43c4ae, 0xda5b5b13, 0x81763a88, 0x95b90733, 0x642b0bca, 0x0dd1dd3a, 0x44d79c88,
            0x095e327f, 0x56abfd80, 0xd0f75ce3, 0x730607c6, 0x89a6fd0e, 0x7fc09cd8, 0xb078f466, 0x184ba518,
            0xaf1ff8e1, 0x99e25f5f, 0x201e00a3, 0x8ba1ae03, 0x19a9942b, 0x2c1e6198, 0x737f2878, 0xc59a2b1c,
            0x7c4f8850, 0xf0409a77, 0x2da9101d, 0xd1aaf46f, 0x291c51af, 0x4d234c8a, 0x68810c4a, 0xfa7e9b84,
            0x75b5dcb8, 0x01fe8b9f, 0xd4acb1ef, 0xbb6ff83b, 0x64805089, 0xf6763925, 0xb327edc3, 0xd688dbca,
            0x196cf19e, 0x2cf2b856, 0xda34c994, 0x7518948f, 0x3170a8d1, 0x30f86e08, 0x681c6bac, 0x3bfddb0c,
            0x5f168b17, 0x4ae631b1, 0x3d105d00, 0x7116b018, 0xad06c9a1, 0x293be0e9, 0x3dd73c5b, 0xbd5a98e8,
            0x75536a9f, 0x818d1508, 0x40e9aa2e, 0x68367be3, 0xaf51e5f2, 0x39b8ccc2, 0x6c67fb45, 0xb13771e2,
            0xe706549e, 0x42b7fc98, 0xedbd44eb, 0x622d9985, 0x85107f0a, 0xcfc9061b, 0xd9e6d66b, 0xe011bb5e,
            0x327eb11e, 0x407ecdd9, 0x3afad29e, 0xab283ffb, 0xbe83d61e, 0xea3e7a46, 0x273b2541, 0xda729e01,
            0x81a62472, 0x8359eedb, 0xa4457261, 0x926ad13f, 0xe7e0c4fe, 0x4d1da2d8, 0xa8b3aed0, 0xb658ec23,
            0xf7e24a58, 0x5d154e58, 0x9a2bf0ba, 0x2eb455b0, 0x50cb61f3, 0x01e095a2, 0xe1de399f, 0xc71cbd35,
            0x7cb90a7e, 0x356e4628, 0x280991f0, 0xd26e64a1, 0xd6c9df5a, 0x3ef127a4, 0xcd2d747b, 0x595f8ee8,
            0xba946959, 0xd8ee7838, 0x010f24d0, 0x86b0eaec, 0x1d078587, 0xfbf516ea, 0xdc2a79f8, 0x7015e404,
            0xe9efdc2c, 0x16bca751, 0xef7b9df7, 0x9227157f, 0xa31e6f81, 0x83b7e291, 0x7ee5c10b, 0x1a196d7c,
            0x23829142, 0x263fe2c2, 0x618461ac, 0xe8681ba5, 0x258b3130, 0xd60cf96a, 0x14fadac6, 0x9d48a438,
            0x8bfa6a79, 0x8b920d34, 0x7e98a7fc, 0x088cc57d, 0x5af407f0, 0x15bc4713, 0x23b64b00, 0x37f96d03,
            0x5ac72f49, 0x065a9a89, 0x16724cac, 0x503ea2fc, 0x4d548e23, 0x2d92d724, 0xdefe753f, 0x37a36c6a,
            0x70708d68, 0xbfe7aba8, 0x1f510c48, 0xb3a995e3, 0x1342df6d, 0x7986ddb9, 0xf4630113, 0xa9f9b81d,
            0xb1dca7ae, 0x70915ce7, 0x780e2c35, 0xd1859a18, 0xf92e7d5a, 0xce573f6b, 0x9d79a6e9, 0xbd7f3425,
            0xacfd81c7, 0x8429a008, 0xf0da82bb, 0x6a5fb409, 0xc567b073, 0x05371eda, 0xa417042a, 0x9d162579,
            0xa8c0eb06, 0xdb7a6342, 0xae0c5575, 0xe0e256a8, 0xa7de78a0, 0x12797538, 0x8e154606, 0x093b23bf,
            0x16f4562d, 0x47067058, 0x6db6dc93, 0x33fc94d2, 0xae60549d, 0x4e1b4fcb, 0x7d4aff37, 0x59b050d1,
            0xd50b787e, 0x4700ba54, 0xc6848ba2, 0xbd8f6801, 0xd2f62909, 0x289cde1e, 0xc5003778, 0xd973a07d,
            0x38a099d8, 0xf8bedbdd, 0x68e9601d, 0x1e86cf6d, 0x2899e89c, 0x229ab04a, 0x45b7a393, 0xef508cd2,
            0xa03448ed, 0x29b47253, 0x9f5a48ac, 0x43c89eec, 0x075dfc10, 0x1c2c3184, 0x3ecca1e3, 0x3c5cc91e,
            0xcf7cd58b, 0xe014d295, 0xed503a24, 0xe452210e, 0x6fe3ac6f, 0xd99dca00, 0x3ae4ac67, 0xd189afe0,
            0xf3ba5df0, 0x84a10e38, 0x4a7b85e4, 0x9d8b4bf7, 0xe83b5b67, 0x90106e3d, 0x534fbf99, 0xce3bcd2e,
        };

        RandomAssert.assertEquals(expectedSequence, rng);
    }
}
