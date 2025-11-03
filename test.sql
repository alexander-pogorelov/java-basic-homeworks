CREATE TABLE public.test (
	id serial4 NOT NULL,
	title varchar(255) NOT NULL,
	description text NULL,
	created_at timestamp DEFAULT now() NOT NULL,
	updated_at timestamp DEFAULT now() NOT NULL,
	CONSTRAINT test__id_pk PRIMARY KEY (id),
	CONSTRAINT test_title_unique UNIQUE (title)
);

CREATE TABLE public.question (
	id serial4 NOT NULL,
	test_id int4 NOT NULL,
	description text NULL,
	question_order int4 DEFAULT 0 NOT NULL,
	created_at timestamp DEFAULT now() NOT NULL,
	updated_at timestamp DEFAULT now() NOT NULL,
	title varchar(255) NOT NULL,
	CONSTRAINT question__id_pk PRIMARY KEY (id),
	CONSTRAINT question_title_unique UNIQUE (title),
	CONSTRAINT test__id__fk FOREIGN KEY (test_id) REFERENCES public.test(id)
);

CREATE TABLE public.answer (
	id serial4 NOT NULL,
	question_id int4 NOT NULL,
	description text NULL,
	is_correct bool DEFAULT false NOT NULL,
	answer_order int4 DEFAULT 0 NOT NULL,
	created_at timestamp DEFAULT now() NOT NULL,
	updated_at timestamp DEFAULT now() NOT NULL,
	title varchar(255) NOT NULL,
	CONSTRAINT answer__id_pk PRIMARY KEY (id),
	CONSTRAINT answer_title_unique UNIQUE (title),
	CONSTRAINT question__id__fk FOREIGN KEY (question_id) REFERENCES public.question(id)
);

CREATE UNIQUE INDEX answer__is_correct__unique ON public.answer USING btree (question_id) WHERE (is_correct = true);
