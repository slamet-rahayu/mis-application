PGDMP                         |            mis_application    13.3    13.3     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    58386    mis_application    DATABASE     s   CREATE DATABASE mis_application WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';
    DROP DATABASE mis_application;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   postgres    false    3            �            1259    58439    logs    TABLE     �   CREATE TABLE public.logs (
    id integer NOT NULL,
    "timestamp" timestamp without time zone,
    message text,
    exception text
);
    DROP TABLE public.logs;
       public         heap    postgres    false    3            �            1259    58437    custom_logs_id_seq    SEQUENCE     �   CREATE SEQUENCE public.custom_logs_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.custom_logs_id_seq;
       public          postgres    false    3    205            �           0    0    custom_logs_id_seq    SEQUENCE OWNED BY     B   ALTER SEQUENCE public.custom_logs_id_seq OWNED BY public.logs.id;
          public          postgres    false    204            �            1259    58390    report    TABLE     �   CREATE TABLE public.report (
    id integer NOT NULL,
    lob character varying,
    cause_of_claim character varying,
    claims_burden double precision,
    date date
);
    DROP TABLE public.report;
       public         heap    postgres    false    3            �            1259    58388    report_id_seq    SEQUENCE     �   CREATE SEQUENCE public.report_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.report_id_seq;
       public          postgres    false    201    3            �           0    0    report_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.report_id_seq OWNED BY public.report.id;
          public          postgres    false    200            �            1259    58412    users    TABLE     w   CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying,
    password character varying
);
    DROP TABLE public.users;
       public         heap    postgres    false    3            �            1259    58410    user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.user_id_seq;
       public          postgres    false    3    203            �           0    0    user_id_seq    SEQUENCE OWNED BY     <   ALTER SEQUENCE public.user_id_seq OWNED BY public.users.id;
          public          postgres    false    202            3           2604    58442    logs id    DEFAULT     i   ALTER TABLE ONLY public.logs ALTER COLUMN id SET DEFAULT nextval('public.custom_logs_id_seq'::regclass);
 6   ALTER TABLE public.logs ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    205    204    205            1           2604    58393 	   report id    DEFAULT     f   ALTER TABLE ONLY public.report ALTER COLUMN id SET DEFAULT nextval('public.report_id_seq'::regclass);
 8   ALTER TABLE public.report ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    200    201    201            2           2604    58415    users id    DEFAULT     c   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    202    203    203            �          0    58439    logs 
   TABLE DATA           C   COPY public.logs (id, "timestamp", message, exception) FROM stdin;
    public          postgres    false    205            �          0    58390    report 
   TABLE DATA           N   COPY public.report (id, lob, cause_of_claim, claims_burden, date) FROM stdin;
    public          postgres    false    201            �          0    58412    users 
   TABLE DATA           7   COPY public.users (id, username, password) FROM stdin;
    public          postgres    false    203            �           0    0    custom_logs_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.custom_logs_id_seq', 43, true);
          public          postgres    false    204            �           0    0    report_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.report_id_seq', 167, true);
          public          postgres    false    200            �           0    0    user_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.user_id_seq', 8, true);
          public          postgres    false    202            9           2606    58447    logs custom_logs_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.logs
    ADD CONSTRAINT custom_logs_pkey PRIMARY KEY (id);
 ?   ALTER TABLE ONLY public.logs DROP CONSTRAINT custom_logs_pkey;
       public            postgres    false    205            5           2606    58398    report report_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.report
    ADD CONSTRAINT report_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.report DROP CONSTRAINT report_pkey;
       public            postgres    false    201            7           2606    58420    users user_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY public.users
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
 9   ALTER TABLE ONLY public.users DROP CONSTRAINT user_pkey;
       public            postgres    false    203            �   �  x��Y]o�6}�~ї��B�S�dtk�%�(�����X-j$�����Etk	Æ�K�{�$�9��#L����f�%�b)N�<��δ��qp[�n��Ci�Bu}Y��;�t���@�$ń�Lۺ��ʡ{m�%2�o+�!ע���vh��z���Cp��#�k�X|���Z�VUiТ15��Ѫ�&�p��L�gt*�������!c���|r��@"�)ΈM���v��*��[]��]㠗������������;3w�=�4�6�����	��Ơ���]���Aokp״U}w������6&|�t{S��?~F�tg�C`����˵n;�z��y�9�"�L*B�d�Ԭ屁Tq���P���4�D�sQ�r�)�Qg!�Y���KiO��\��3�upsg�����o�/��eo��5zws}q��>6�l��a�Z����1�x��<4��^�h%PQ�E��������׸���ou���*�i�5��A �-��ZS�����~��W��֠�F[��&���V�:��\���`���*��c��x���CK#h��"�%?鹚Ũ�/ƚM����mtӘe@���Z�yLhۗJf8��
阳ZD�9S�`~�2���>��p�+����LqzPu?ꁄ Z',�N�9�Q�5�0ɱ���j���.��g��Q�N�M�f���	��m�����P7̋5�U��u��k�y���ͺ~����� �Nôf�wf�B��]��8* ��� ����"�Lpv�6��Y{�D�'������M�p;�����J���
E�&`8�U�c�O;�)��QǼ!R%8.N[�7DN���ǉǏz�L��#�c�Ơ3��8�OZ�,BMy��҃=�xN��U���p���wU��zT���M8<�L:� Mw�FZ�ʚ�� �aI�9��oA�a��!ۃŊ��٠;���-~!F��abZ�p�$_ Բ�      �   \  x����N[1����K��x�?�UT�*u�Mڦ����7��\Ńϰ��v�1��[��?-ߎO�ߟE���Ky��]��e����.a���|����,������t{{���1�/"�!���6.c�y|��|�t�c�������q��I�9|�n���p|d�m��S�,nй�.�����_o��1Ԇ����MM����$6�7|�_V���</T�y�*)t%����od��4�o ���'���3��?#���3¯~��+�_!�
�W�B�¯~��_%�*�W	�J�U¯~��_%����F��<m�Mi�m�m+�F�j�+�l��G��)�h;���K���h�]#��[���m��mo�F�I��5�T$R�H�֑�,��-��.��/��0��1�I$ �2����Ku8^ l*�D�ƀ/�Q�/����yc��#��"?@F�x��K�h	:��d���ҕNJ7��Ϭ���2;A�	�N(��N`$3ӈ2!����%T�%��$�%T�P���c	�SmKh�Z�|�87"���%�3,G-a��ċZ���ı���>��l���&?��l����?����պ� 1�p      �   V   x���,(�L�M�H��T1JT14P���3�w1�JI����0(4�0�*��//�*Is4��(�H-�/w��	ML2N��)0������ ���          �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    58386    mis_application    DATABASE     s   CREATE DATABASE mis_application WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';
    DROP DATABASE mis_application;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   postgres    false    3            �            1259    58439    logs    TABLE     �   CREATE TABLE public.logs (
    id integer NOT NULL,
    "timestamp" timestamp without time zone,
    message text,
    exception text
);
    DROP TABLE public.logs;
       public         heap    postgres    false    3            �            1259    58437    custom_logs_id_seq    SEQUENCE     �   CREATE SEQUENCE public.custom_logs_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.custom_logs_id_seq;
       public          postgres    false    3    205            �           0    0    custom_logs_id_seq    SEQUENCE OWNED BY     B   ALTER SEQUENCE public.custom_logs_id_seq OWNED BY public.logs.id;
          public          postgres    false    204            �            1259    58390    report    TABLE     �   CREATE TABLE public.report (
    id integer NOT NULL,
    lob character varying,
    cause_of_claim character varying,
    claims_burden double precision,
    date date
);
    DROP TABLE public.report;
       public         heap    postgres    false    3            �            1259    58388    report_id_seq    SEQUENCE     �   CREATE SEQUENCE public.report_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.report_id_seq;
       public          postgres    false    201    3            �           0    0    report_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.report_id_seq OWNED BY public.report.id;
          public          postgres    false    200            �            1259    58412    users    TABLE     w   CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying,
    password character varying
);
    DROP TABLE public.users;
       public         heap    postgres    false    3            �            1259    58410    user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.user_id_seq;
       public          postgres    false    3    203            �           0    0    user_id_seq    SEQUENCE OWNED BY     <   ALTER SEQUENCE public.user_id_seq OWNED BY public.users.id;
          public          postgres    false    202            3           2604    58442    logs id    DEFAULT     i   ALTER TABLE ONLY public.logs ALTER COLUMN id SET DEFAULT nextval('public.custom_logs_id_seq'::regclass);
 6   ALTER TABLE public.logs ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    205    204    205            1           2604    58393 	   report id    DEFAULT     f   ALTER TABLE ONLY public.report ALTER COLUMN id SET DEFAULT nextval('public.report_id_seq'::regclass);
 8   ALTER TABLE public.report ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    200    201    201            2           2604    58415    users id    DEFAULT     c   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    202    203    203            �          0    58439    logs 
   TABLE DATA           C   COPY public.logs (id, "timestamp", message, exception) FROM stdin;
    public          postgres    false    205   f
       �          0    58390    report 
   TABLE DATA           N   COPY public.report (id, lob, cause_of_claim, claims_burden, date) FROM stdin;
    public          postgres    false    201   �       �          0    58412    users 
   TABLE DATA           7   COPY public.users (id, username, password) FROM stdin;
    public          postgres    false    203   f       �           0    0    custom_logs_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.custom_logs_id_seq', 43, true);
          public          postgres    false    204            �           0    0    report_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.report_id_seq', 167, true);
          public          postgres    false    200            �           0    0    user_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.user_id_seq', 8, true);
          public          postgres    false    202            9           2606    58447    logs custom_logs_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.logs
    ADD CONSTRAINT custom_logs_pkey PRIMARY KEY (id);
 ?   ALTER TABLE ONLY public.logs DROP CONSTRAINT custom_logs_pkey;
       public            postgres    false    205            5           2606    58398    report report_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.report
    ADD CONSTRAINT report_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.report DROP CONSTRAINT report_pkey;
       public            postgres    false    201            7           2606    58420    users user_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY public.users
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
 9   ALTER TABLE ONLY public.users DROP CONSTRAINT user_pkey;
       public            postgres    false    203           